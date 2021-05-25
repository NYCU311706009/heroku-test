package com.example.demo.controller;

import com.example.demo.Entity.LoginParams;
import com.example.demo.service.CustomerService;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/index")
    public String testGet(Model model) {
        // 方法一：通過SecurityContextHolder獲取
        System.out.println("hi");
        model.addAttribute("isLogin", "");
        try{
            Customer c = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(c.getId());
            if (c.getId() !=null) {
                model.addAttribute("username",c.getUsername());
                c.setLogin(true);
                model.addAttribute("isLogin", "_login");
            }
        }catch (Exception e){
            model.addAttribute("isLogin","");
            return "index";
        }
        return "index";
    }

    //@ModelAttribute("customer")Customer customer
}
