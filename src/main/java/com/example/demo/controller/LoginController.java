package com.example.demo.controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.LoginParams;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;


    @GetMapping("/login")
    public String loginGet(){
        return "login";
    }
    @PostMapping("/login")
    public String loginPost(LoginParams loginParams){
        if(loginService.login(loginParams)){
            return "index";
        }
        return "login";
    }
}
