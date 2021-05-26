package com.example.demo.service;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.LoginParams;
import com.example.demo.Entity.RegisterParams;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean login(LoginParams loginParams){

        Customer current = customerRepository.findByUsername(loginParams.getUsername());

        System.out.println("boolean login"+current.getUsername());
        System.out.println("current"+current.getPassword());
        System.out.println("loginParam"+passwordEncoder.encode(loginParams.getPassword()));

        if(current.getPassword().equals(passwordEncoder.encode(loginParams.getPassword()))){
            return true;
        }
        return false;
    }

    public Customer isLogin(String username) {
        if (customerRepository.findByUsername(username) !=null) {
            return customerRepository.findByUsername(username);
        }
        return null;
    }
    @Override
    public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByUsername(username);
    }

}
