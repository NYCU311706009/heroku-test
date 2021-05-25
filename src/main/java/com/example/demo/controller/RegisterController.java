package com.example.demo.controller;

import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("isSuccess",true);
        //model.addAttribute("errStatus","帳號錯誤");
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(Model model, @RequestParam("username") String account , @RequestParam("password") String password,
                               @RequestParam("chineseName") String username, @RequestParam("birth") String birth, @RequestParam("twId") String twId,
                               @RequestParam("phone") String phone){
        if(registerService.isExist(username, password)){
            model.addAttribute("isSuccess",false);
            model.addAttribute("errStatus","此帳號名稱可能已被使用");
            return "register";
        }else {
            registerService.register(account,password,username,birth,twId,phone);
            model.addAttribute("isLogin", "");
            return "redirect:/index";
        }
//        if(customerService.regist(customer)){
//            return "sign";
//        }
//        Customer c = customerService.findByUsername(customer.getUsername());
//        System.out.println(c.getZid());
//        return "register";
    }
}
