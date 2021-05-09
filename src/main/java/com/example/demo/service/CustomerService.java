package com.example.demo.service;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
        在實作Login時，
        1.先查看該customer是否在資料庫存在
        2.確認其輸入密碼與資料庫裡儲存的是否相同 =>要記得將輸入的password做Encode
        3.回傳結果 true/false
     */
    public Boolean login(Customer customer){
       Customer current = customerRepository.findByAccount(customer.getAccount());  
       if(current.getPassword().equals(passwordEncoder.encode(customer.getPassword()))){
           return true;
       }
       return false;
    }

    public Boolean isExist(String account,String password){
        if(customerRepository.findByUsername(account)!=null||customerRepository.findByPassword(password)!=null){
            return true;
        }
        return false;
    }
    public void register(String account,String password,String username,String birth,String twId,String phone){
        Customer customer = new Customer.Builder()
                .setAccount(account)
                .setPassword(passwordEncoder.encode(password))
                .setUsername(username)
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
    public Customer findByUsername(String username){
        return customerRepository.findByUsername(username);
    }
    @Override
    public Customer loadUserByUsername(String account) throws UsernameNotFoundException {
        Customer c = customerRepository.findByUsername(account);
        return c;
    }

}
