package com.example.demo.service;

import com.example.demo.Entity.*;
import com.example.demo.Repository.ConfirmationTokenRepository;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailService emailService;

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
        Customer c = customerRepository.findByUsername(registerParams.getUsername());
        System.out.println(c.toString());
        //TODO: Email Verification
        //Create token
        String token = UUID.randomUUID().toString();
        CurrentTime currentTime = new CurrentTime();
        ConfirmationToken confirmationToken = new ConfirmationToken(customer.getUsername(), token,currentTime.getTime());
        confirmationTokenRepository.save(confirmationToken);
        //Send mail
        EmailSender emailSender = new EmailSender(c.getEmail(),c.getUsername(),token);
        String url = emailSender.getUrl();
        emailService.send(c.getEmail(),url);
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
    public Boolean isExistByEmail(RegisterParams registerParams){
        String email = registerParams.getEmail();
        if(customerRepository.findByEmail(email)!=null){
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
