package com.example.demo.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailSender {
    String email;
    String username;
    String url;
    public EmailSender(String email,String username,String token){
        this.email = email;
        this.username = username;
        this.url = buildContent(username,token);
    }

    private String buildContent(String username,String token) {
        String url = "http://localhost:8080/api/"+username+"/"+token;
        return url;
    }
}
