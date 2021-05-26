package com.example.demo;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.LoginParams;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repository.CustomerRepository;

import com.example.demo.controller.LoginController;
import com.example.demo.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    LoginController loginController;



    @Test
    void createNewUser() {
        Customer c = new Customer.Builder()
                .setUsername("b10733033")
                .setPassword(passwordEncoder.encode("b10733033"))
                //.setPassword("b10733033")
                .setChineseName("王廣")
                .setRole(UserRole.valueOf("ADMIN"))
                .build();
        customerRepository.save(c);
        System.out.println(customerRepository.findByUsername(c.getUsername()).toString());
    }
    @Test
    void loginTest(){
        LoginParams loginParams = new LoginParams("b10733033","b10733033");
        String result = loginController.loginPost(loginParams);
        System.out.println(result);
    }
}
