package com.example.demo.controller;

import com.example.demo.service.MyOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyOilController {
    @Autowired
    MyOilService myOilService;

    @GetMapping("/myoil")
    public String mOilGet(){
        return "myoil";
    }

    @PostMapping("/myoil")
    public String mOilPost(){
        return "myoil";
    }
}
