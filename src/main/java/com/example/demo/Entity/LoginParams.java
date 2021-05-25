package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginParams {
    private String username;
    private String password;
    public LoginParams(String username, String password){
        this.username = username;
        this.password = password;
    }
}
