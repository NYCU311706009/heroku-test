package com.example.demo.controller;

import com.example.demo.Entity.RegisterParams;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {


    @Autowired
    RegisterService registerService;

    /*
    *  Always remember to prevent a single user to login multiple times without logout first.
    * */
    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }


    @PostMapping("/register")
    public String registerPost(RegisterParams registerParams){
        System.out.println(registerParams.toString());
        if(!registerService.isExist(registerParams)){
            registerService.register(registerParams);
            return "login";
        }else{
            System.out.println("username may be used");
            return "register";
        }
    }
}
