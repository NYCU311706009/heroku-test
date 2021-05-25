package com.example.demo.controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.LoginParams;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;


    @GetMapping("/login")
    public String signGet(Model model){
        model.addAttribute("loginParams",new LoginParams("",""));
        return "login";
    }
    @PostMapping("/login")
    public String signPost(Model model, @RequestParam("loginParams") LoginParams loginparams){

        if(loginService.login(loginparams)){
            Customer curr_customer = loginService.findByUsername(loginparams.getUsername());
            model.addAttribute("customer",curr_customer);
            System.out.println(curr_customer.getUsername());
            return "index";
        }
        return "login";

    }
}
