package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@AllArgsConstructor
public class RegisterParams {
    String account;
    String password;
    String username;
    String birth;
    String twId;
    String phone;
}
