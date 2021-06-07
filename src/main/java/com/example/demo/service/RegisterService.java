package com.example.demo.service;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.RegisterParams;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void register(RegisterParams registerParams){
        Customer customer = new Customer.Builder()
                .setUsername(registerParams.getUsername())
                .setPassword(passwordEncoder.encode(registerParams.getPassword()))
                .setChineseName(registerParams.getChineseName())
                .setBirth(registerParams.getBirth())
                .setTwId(registerParams.getTwId())
                .setPhone(registerParams.getPhone())
                .setEmail(registerParams.getEmail())
                .setValid(true)
                .setLogin(true)
                .setRole(UserRole.USER)
                .build();
        customerRepository.save(customer);
        System.out.println("save");
        System.out.println(customerRepository.findByUsername(registerParams.getUsername()).toString());
    }


    /**
     *  Check if user is already registered.
     */
    public Boolean isExist(RegisterParams registerParams){
        String registerUsername = registerParams.getUsername();
        if(customerRepository.findByUsername(registerUsername)!=null){
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

    /**
     * member is modified or not,if True then save and logout.
     */
    public boolean isChange(Customer curr, String username, String email, String birth, String phone, String twId) {
        Boolean changed = false;

        if (!curr.getUsername().equals(username)){
            curr.setUsername(username);
            changed = true;
        }
        if (!curr.getEmail().equals(email)){
            curr.setEmail(email);
            changed = true;
        }
        if (!curr.getBirth().equals(birth)){
            curr.setBirth(birth);
            changed = true;
        }
        if (!curr.getPhone().equals(phone)){
            curr.setPhone(phone);
            changed = true;
        }
        if (!curr.getTwId().equals(twId)){
            curr.setTwId(twId);
            changed = true;
        }
        if (changed){
            customerRepository.save(curr);
        }
        return changed;
    }
}
