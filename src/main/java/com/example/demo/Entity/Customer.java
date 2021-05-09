package com.example.demo.Entity;




import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String account;     //帳號
    private String password;    //密碼
    private String username;    //姓名
    private String role;        //角色          沒用到
    private String birth;       //出生日期      yyyy/mm/dd
    private String twId;        //身分證字號     pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$
    private String phone;       //電話號碼      pattern="^09[0-9]{8}$

    private boolean isLogin;    //登入狀態
    private boolean isValid;    //驗證信
//    @OneToMany
//    private List<Product> products;

    private Customer(Builder builder){
        this.account = builder.account;
        this.password = builder.password;
        this.username = builder.username;
        this.role = builder.role;
        this.birth = builder.birth;
        this.twId = builder.twId;
        this.phone = builder.phone;
        this.isLogin = builder.isLogin;
        this.isValid = builder.isValid;
    }

    public Customer() {

    }


    public static class Builder {
        private String account;     //帳號
        private String password;    //密碼
        private String username;    //姓名
        private String role;        //角色          沒用到
        private String birth;       //出生日期      yyyy/mm/dd
        private String twId;        //身分證字號     pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$
        private String phone;       //電話號碼      pattern="^09[0-9]{8}$
        private boolean isLogin = true;    //登入狀態
        private boolean isValid = true;    //驗證信
        public Builder(){ }
        public Builder setAccount(String account) {
            this.account = account;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setBirth(String birth) {
            this.birth = birth;
            return this;
        }

        public Builder setTwId(String twId) {
            this.twId = twId;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setLogin(boolean login) {
            isLogin = true;
            return this;
        }

        public Builder setValid(boolean valid) {
            isValid = true;
            return this;
        }
        public Customer build() {
            return new Customer(this);
        }


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Arrays.asList(authority);
    }


    @Override
    public boolean isAccountNonExpired() {
        return isLogin;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLogin;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isValid;
    }

    @Override
    public boolean isEnabled() {
        return isLogin;
    }
}
