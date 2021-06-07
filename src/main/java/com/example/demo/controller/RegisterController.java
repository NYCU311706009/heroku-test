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

    RegisterParams registerParams;
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
            this.registerParams = registerParams;
            //registerService.register(registerParams);
            return "redirect:/register2";
        }else{
            System.out.println("username may be used");
            return "register";
        }
    }
    @GetMapping("/register2")
    public String register2Get(){return "register2";}
    @PostMapping("/register2")
    public String register2Post(String email){
        registerParams.setEmail(email);
        registerService.register(registerParams);
        return "redirect:/index";
    }
}
