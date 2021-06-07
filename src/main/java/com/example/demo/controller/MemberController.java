package com.example.demo.controller;

import com.example.demo.Entity.Customer;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    LoginService loginService;
    @Autowired
    RegisterService registerService;

    @GetMapping("/member")
    public String memberGet(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "index";
        }
        Customer curr = loginService.loadUserByUsername(auth.getName());
        model
                .addAttribute("isLogin",true)
                .addAttribute("chineseName", curr.getChineseName());

        model
                .addAttribute("username",curr.getUsername())
                .addAttribute("email",curr.getEmail())
                .addAttribute("birth",curr.getBirth())
                .addAttribute("phone",curr.getPhone())
                .addAttribute("twId",curr.getTwId());
        return "member";
    }

    @PostMapping("/member")
    public String memberPost(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "index";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName());

        Customer curr = loginService.loadUserByUsername(auth.getName());
        String username = (String) model.getAttribute("username");
        String email = (String) model.getAttribute("email");
        String birth = (String) model.getAttribute("birth");
        String phone = (String) model.getAttribute("phone");
        String twId = (String) model.getAttribute("twId");
        if(registerService.isChange(curr,username,email,birth,phone,twId)){
            return "redirect:/logout";
        }
        return "member";
    }


}
