package com.example.demo.controller;

import com.example.demo.Entity.LoginParams;
import com.example.demo.service.CustomerService;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CustomerController {

    @Autowired
    LoginService loginService;

    @GetMapping("/index")
    public String indexGet(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
//        model.addAttribute("username", auth.getName())
//                .addAttribute("roles", auth.getAuthorities());
        return "index";
    }
}
