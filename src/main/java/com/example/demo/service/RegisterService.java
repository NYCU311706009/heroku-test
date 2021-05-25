package com.example.demo.service;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(String account,String password,String username,String birth,String twId,String phone){
        Customer customer = new Customer.Builder()
                .setUsername(account)
                .setPassword(passwordEncoder.encode(password))
                .setChineseName(username)
                .setBirth(birth)
                .setTwId(twId)
                .setPhone(phone)
                .setValid(true)
                .setLogin(true)
                .setRole("USER")
                .build();
        customerRepository.save(customer);
        System.out.println("saved");
    }

    public Boolean isExist(String username,String password){
        if(customerRepository.findByUsername(username)!=null||customerRepository.findByPassword(password)!=null){
            return true;
        }
        return false;
    }
}
