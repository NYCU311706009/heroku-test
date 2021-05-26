package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class LoginParams {
    private String username;
    private String password;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    public LoginParams(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginParams{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
