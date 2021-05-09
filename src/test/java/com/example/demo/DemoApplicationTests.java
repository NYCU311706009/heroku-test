package com.example.demo;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;

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
//    @Test
//    void contextLoads() {
//        Customer c = new Customer("ccc",passwordEncoder.encode("ccc"));
//        c.setRole("admin");
//        customerRepository.save(c);
//    }

}
