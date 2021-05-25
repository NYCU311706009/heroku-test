package com.example.demo.service;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.LoginParams;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean login(LoginParams loginParams){
        Customer current = customerRepository.findByUsername(loginParams.getUsername());
        if(current.getPassword().equals(passwordEncoder.encode(loginParams.getPassword()))){
            return true;
        }
        return false;
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
