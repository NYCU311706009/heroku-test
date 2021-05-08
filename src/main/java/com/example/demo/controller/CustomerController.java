package com.example.demo.controller;

import com.example.demo.Entity.CustomUser;
import com.example.demo.service.CustomerService;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;
//
//    @GetMapping("/web")
//    public String web(Model model){
//        CustomUser cu = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Customer c = cu.getCustomer();
//        model.addAttribute("customer",c);
//        return "web";
//    }
//    @PostMapping("/web")
//    public ModelAndView webSubmit(ModelMap model, @ModelAttribute("customer") Customer c/*, HttpServletResponse response*/){
//        if(customerService.login(c)){
//            Customer curr_customer = customerService.findByUsername(c.getUsername());
//            model.addAttribute("customer",curr_customer);
//        }
//        return new ModelAndView("index_test", model);
//        /*
//        Cookie cookie = new Cookie("username",curr_customer.getUsername());
//        response.addCookie(cookie);
//        */
//    }

    //首頁
    @GetMapping("/index")
    public String home(Model model) {
        // 方法一：通過SecurityContextHolder獲取
        CustomUser cu = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer c = cu.getCustomer();
        model.addAttribute("customer",c);
        return "index";
    }
    @GetMapping("/test")
    public String testGet(Model model) {
        // 方法一：通過SecurityContextHolder獲取
        System.out.println("hi");
        try{
            CustomUser cu = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Customer c = cu.getCustomer();
            System.out.println(c.getId());
            if (c.id !=null) {
                model.addAttribute("username",c.getUsername());
                model.addAttribute("isLogin", "_login");
            }
        }catch (Exception e){
            model.addAttribute("isLogin","");
            return "test";
        }
        return "test";
    }
//    @PostMapping(value="/index")
//    public ModelAndView indexPost(ModelMap model){
//        model.addAttribute("customer",model.getAttribute("customer"));
//        return new ModelAndView("index", model);
//    }

    //登入流程
//    @GetMapping("/login")
//    public String login(Model model, Customer c) {
//        model.addAttribute("customer",c);
//        return "login";
//    }
//    @PostMapping("/login")
//    public ModelAndView loginSubmit(ModelMap model, @ModelAttribute("customer") Customer c/*, HttpServletResponse response*/){
//        if(customerService.login(c)){
//            Customer curr_customer = customerService.findByUsername(c.getUsername());
//            model.addAttribute("customer",curr_customer);
//        }
//        return new ModelAndView("index_test", model);
//        /*
//        Cookie cookie = new Cookie("username",curr_customer.getUsername());
//        response.addCookie(cookie);
//        */
//    }
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
//    @GetMapping("/friend")
//    public ModelAndView friendGet(ModelMap model,Customer c,@CookieValue(value = "username",
//            defaultValue = "null") String username){
//        System.out.println(username);
//        Customer curr_customer = customerService.findByUsername(username);
//        model.addAttribute("customer",curr_customer);
//        return new ModelAndView("friend",model);
//    }
    @GetMapping("/register")
    public String registeTestForm(Model model,Customer customer){
        model.addAttribute("customer",customer);
        model.addAttribute("isSuccess",true);
        model.addAttribute("errStatus","帳號錯誤");
        return "register";
    }
    @PostMapping("/register")
    public String registeSubmit(Model model, @ModelAttribute("customer")Customer customer){
        if(customerService.regist(customer)){
            return "sign";
        }
        Customer c = customerService.findByUsername(customer.getUsername());
        System.out.println(c.getZid());
        return "register";
    }


}
