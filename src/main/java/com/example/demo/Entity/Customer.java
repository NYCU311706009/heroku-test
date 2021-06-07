package com.example.demo.Entity;




import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;     //帳號
    private String password;    //密碼
    private String chineseName;    //姓名
    private UserRole role;        //角色          沒用到
    private String birth;       //出生日期      yyyy/mm/dd
    private String twId;        //身分證字號     pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$
    private String phone;       //電話號碼      pattern="^09[0-9]{8}$
    private String email;       //信箱
    private boolean isLogin;    //登入狀態
    private boolean isValid;    //驗證信


//    @OneToMany
//    private List<Order> Orders;

//    public void addOrders(Order order) {
//        Orders.add(order);
//    }

    private Customer(Builder builder){
        this.username = builder.username;
        this.password = builder.password;
        this.chineseName = builder.chineseName;
        this.role = builder.role;
        this.birth = builder.birth;
        this.twId = builder.twId;
        this.phone = builder.phone;
        this.isLogin = builder.isLogin;
        this.isValid = builder.isValid;
        this.email = builder.email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", role=" + role +
                ", birth='" + birth + '\'' +
                ", twId='" + twId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isLogin=" + isLogin +
                ", isValid=" + isValid +
                '}';
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public static class Builder {
        private String username;     //帳號
        private String password;    //密碼
        private String email;       //信箱
        private String chineseName;    //姓名
        private UserRole role;        //角色          沒用到
        private String birth;       //出生日期      yyyy/mm/dd
        private String twId;        //身分證字號     pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$
        private String phone;       //電話號碼      pattern="^09[0-9]{8}$
        private boolean isLogin = true;    //登入狀態
        private boolean isValid = true;    //驗證信
        public Builder(){ }
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setChineseName(String chineseName) {
            this.chineseName = chineseName;
            return this;
        }

        public Builder setRole(UserRole role) {
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
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(UserRole.USER.name());
        return Collections.singletonList(authority);
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
