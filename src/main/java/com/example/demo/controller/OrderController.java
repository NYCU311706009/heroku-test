package com.example.demo.controller;

import com.example.demo.Entity.CreditCardParams;
import com.example.demo.Entity.OrderParams;
import com.example.demo.service.LoginService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    LoginService loginService;



    private static OrderParams mOrderParams;
    private static Authentication auth;
    @GetMapping("/order")
    public String orderGet(Model model){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
             .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        return "order";
    }

    @PostMapping("/order")
    public String orderPost(Model model,OrderParams orderParams){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        if (orderParams!=null){
            mOrderParams = orderParams;
            mOrderParams.setOwner(auth.getName());
            return "redirect:/order2";
        }
        return "order";
    }

    @GetMapping("/order2")
    public String order2Get(Model model){
        try{
            auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName().equals("anonymousUser")){
                return "redirect:/index";
            }
            System.out.println("It's "+auth.getName());
            model.addAttribute("isLogin",true)
                    .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                    .addAttribute("rightmenu", "rightmenu_login");
            System.out.println("order2:"+mOrderParams.toString());
            return "order2";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "redirect:/index";
        }
    }

    @PostMapping("/order2")
    public String order2Post(Model model,CreditCardParams creditCardParams){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName());
        if(creditCardParams!=null){
            System.out.println(creditCardParams.toString());
            mOrderParams.setCreditCardParams(creditCardParams);
            return "redirect:/order3";
        }
        return "/order2";
    }

    @GetMapping("/order3")
    public String order3Get(Model model){
        try{
            auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName().equals("anonymousUser")){
                return "redirect:/index";
            }
            System.out.println("It's "+auth.getName());
            model.addAttribute("isLogin",true)
                    .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                    .addAttribute("rightmenu", "rightmenu_login");
            mOrderParams.getOilType();

            System.out.println("order3:"+mOrderParams.getOwner());
            model
                    .addAttribute("oilType",mOrderParams.getOilType())
                    .addAttribute("oilVolume",(int)mOrderParams.getOilVolume())
                    .addAttribute("startMonth",mOrderParams.getStartMonth()+" ")
                    .addAttribute("endMonth"," "+mOrderParams.getEndMonth())
                    .addAttribute("cardNumber",
                            mOrderParams.getCreditCardParams().getCardNumber().substring(0,4)+
                                    "********"+mOrderParams.getCreditCardParams().getCardNumber().substring(12))
                    .addAttribute("handleFee",(int)mOrderParams.getHandleFee())
                    .addAttribute("cpcPrice",getCpcPrice(mOrderParams.getOilType()))
                    .addAttribute("oilCost",(int)mOrderParams.getOilCost());
            return "order3";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "redirect:/index";
        }
    }

    private Double getCpcPrice(String oilType) {
        double cpcPrice = 0;
        switch (oilType){
            case "92無鉛":
                cpcPrice = 26.6;
                break;
            case "95無鉛":
                cpcPrice = 28.1;
                break;
            case "98無鉛":
                cpcPrice = 30.1;
                break;
            case "超級柴油":
                cpcPrice = 23.8;
                break;
        }
        return cpcPrice;
    }

    @PostMapping("/order3")
    public String order3Post(Model model){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName());
        return "redirect:/order4";
    }
    @GetMapping("/order4")
    public String order4Get(Model model){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login")
                .addAttribute("cvvError",false);
        return "/order4";
    }

    @PostMapping("/order4")
    public String order4Post(Model model ,String cardCvv){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                .addAttribute("rightmenu", "rightmenu_login");
        if (cardCvv.equals(mOrderParams.getCreditCardParams().getCardCvv())){
            orderService.saveOrder(mOrderParams);
            return "redirect:/order5";
        }
        model.addAttribute("cvvError",true);

        return "order4";
    }
    @GetMapping("/order5")
    public String order5Get(Model model){
        try{
            auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName().equals("anonymousUser")){
                return "redirect:/index";
            }
            System.out.println("It's "+auth.getName());
            model.addAttribute("isLogin",true)
                    .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName())
                    .addAttribute("rightmenu", "rightmenu_login");
            System.out.println("order5:"+mOrderParams.getOwner());
            model
                    .addAttribute("oilType",mOrderParams.getOilType())
                    .addAttribute("oilVolume",(int)mOrderParams.getOilVolume())
                    .addAttribute("startMonth",mOrderParams.getStartMonth()+" ")
                    .addAttribute("endMonth"," "+mOrderParams.getEndMonth());
            return "order5";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "redirect:/index";
        }
    }

    @PostMapping("/order5")
    public String order5Post(Model model){
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/index";
        }
        System.out.println("It's "+auth.getName());
        model.addAttribute("isLogin",true)
                .addAttribute("chineseName", loginService.loadUserByUsername(auth.getName()).getChineseName());
        return "order5";
    }





}
