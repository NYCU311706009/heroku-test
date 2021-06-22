package com.example.demo.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String token;
    private String createdTime;
    private String confirmedTime;

    public ConfirmationToken(String username,String token, String createdTime) {
        this.username = username;
        this.token = token;
        this.createdTime = createdTime;
    }
}
