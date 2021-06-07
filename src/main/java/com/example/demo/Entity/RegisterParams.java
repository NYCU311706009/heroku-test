package com.example.demo.Entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParams {
    private String username;
    private String password;
    private String chineseName;
    private String birth;
    private String twId;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return "RegisterParams{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", birth='" + birth + '\'' +
                ", twId='" + twId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
