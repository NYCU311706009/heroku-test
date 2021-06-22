package com.example.demo.controller;

import com.example.demo.service.AuthMailService;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api",produces="application/json")
public class VerificationController {
    @Autowired
    AuthMailService authMailService;
    @Autowired
    LoginService loginService;

    @GetMapping("/{username}/{token}")
    public String verifyGet(@PathVariable String username, @PathVariable String token){

        String result = username+":"+token+"\n\n"+"http://localhost:8080/index";
        if (authMailService.confirm(username,token)){
            return result;
        }
        System.out.println(username+":"+loginService.loadUserByUsername(username));
        result = "account has been confirmed or unexist"+"\n\n"+"http://localhost:8080/index";
        return result;
    }

}
