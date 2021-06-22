package com.example.demo.controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.NewOrder;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    LoginService loginService;

    @GetMapping("/history")
    public String historyGet(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "index";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        Customer curr = loginService.loadUserByUsername(auth.getName());
        List<NewOrder> Orders = orderRepository.findByOwner(curr.getUsername());
        model.addAttribute("newOrder",Orders);
        return "/history";
    }
}
