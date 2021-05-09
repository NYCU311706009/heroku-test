package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @GetMapping("/test")
    public String testGet(Model model) {
        // 方法一：通過SecurityContextHolder獲取
        System.out.println("hi");
        model.addAttribute("isLogin", "");
        try{
            Customer c = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(c.getId());
            if (c.getId() !=null) {
                model.addAttribute("username",c.getUsername());
                c.setLogin(true);
                model.addAttribute("isLogin", "_login");
            }
        }catch (Exception e){
            model.addAttribute("isLogin","");
            return "test";
        }
        return "test";
    }

    @GetMapping("/sign")
    public String signGet(Model model,Customer customer){
        //model.addAttribute("customer",customer);
        return "sign";
    }
    @PostMapping("/sign")
    public String signPost(Model model, @ModelAttribute("customer")Customer customer){
        System.out.println(customer.getId()+"出現");
        if(customerService.login(customer)){
            Customer curr_customer = customerService.findByUsername(customer.getUsername());
            model.addAttribute("customer",curr_customer);
            System.out.println(curr_customer.getId());
            return "test";
        }else{
            return "sign";
        }
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("isSuccess",true);
        //model.addAttribute("errStatus","帳號錯誤");
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(Model model, @RequestParam("account") String account ,@RequestParam("password") String password,
                                @RequestParam("username") String username,@RequestParam("birth") String birth,@RequestParam("twId") String twId,
                                @RequestParam("phone") String phone){
        if(customerService.isExist(account, password)){
            model.addAttribute("isSuccess",false);
            model.addAttribute("errStatus","此帳號名稱可能已被使用");
            return "register";
        }else {
            customerService.register(account,password,username,birth,twId,phone);
            model.addAttribute("isLogin", "");
            return "redirect:/test";
        }
//        if(customerService.regist(customer)){
//            return "sign";
//        }
//        Customer c = customerService.findByUsername(customer.getUsername());
//        System.out.println(c.getZid());
//        return "register";
    }

    //@ModelAttribute("customer")Customer customer
}
