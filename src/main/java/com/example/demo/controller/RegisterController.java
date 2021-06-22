package com.example.demo.controller;

import com.example.demo.Entity.RegisterParams;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String registerGet(Model model){
        model.addAttribute("rightmenu", "rightmenu");
        return "register";
    }


    @PostMapping("/register")
    public String registerPost(Model model,RegisterParams registerParams){
        System.out.println(registerParams.toString());
        if(!registerService.isExist(registerParams)){
            this.registerParams = registerParams;
            //registerService.register(registerParams);
            return "redirect:/register2";
        }else{
            System.out.println("username may be used");
            model.addAttribute("paramUsed",true);
            return "register";
        }
    }
    @GetMapping("/register2")
    public String register2Get(Model model){
        model.addAttribute("rightmenu", "rightmenu");
        return "register2";
    }
    @PostMapping("/register2")
    public String register2Post(Model model,String email){
        registerParams.setEmail(email);
        if (registerService.isExistByEmail(registerParams)){
            model.addAttribute("paramUsed",true);
            return "register2";
        }
        registerService.register(registerParams);
        return "redirect:/index";
    }
}
