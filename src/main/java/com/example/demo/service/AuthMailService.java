package com.example.demo.service;

import com.example.demo.Entity.ConfirmationToken;
import com.example.demo.Entity.CurrentTime;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.ConfirmationTokenRepository;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthMailService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    CustomerRepository customerRepository;
    public Boolean confirm(String username, String token){

        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        confirmationToken.setConfirmedTime(new CurrentTime().getTime());
        Customer customer = customerRepository.findByUsername(confirmationToken.getUsername());
        //如果已經驗證過
        if (customer.isValid()){
            return false;
        }
        customer.setValid(true);
        confirmationTokenRepository.save(confirmationToken);
        customerRepository.save(customer);
        System.out.println(confirmationToken.getToken()+": is confirmed.");
        return true;
    }
}
