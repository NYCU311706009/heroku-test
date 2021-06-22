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
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "index";
        }
        model.addAttribute("isLogin",true)
             .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
             .addAttribute("rightmenu", "rightmenu_login");

        return "index";
    }

    @GetMapping("/about")
    public String aboutGet(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "/about";
        }
        model.addAttribute("isLogin",true)
            .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
            .addAttribute("rightmenu", "rightmenu_login");
        return "/about";
    }
    @GetMapping("/disclaimer")
    public String aboutDisclaimer(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "/disclaimer";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        return "/disclaimer";
    }
    @GetMapping("/privacy")
    public String aboutPrivacy(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "/privacy";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        return "/privacy";
    }
    @GetMapping("/rights")
    public String aboutRights(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "/rights";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        return "/rights";
    }
    @GetMapping("/service")
    public String aboutService(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "/service";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        return "/service";
    }
    @GetMapping("/terms")
    public String aboutTerms(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("It's"+auth.getName());
        if (auth.getName().equals("anonymousUser")){
            model.addAttribute("rightmenu", "rightmenu");
            return "/terms";
        }
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        return "/terms";
    }
}
