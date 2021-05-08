package com.example.demo.Entity;




import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
public class Customer  {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String getZid() {
        return Zid;
    }

    public void setZid(String zid) {
        Zid = zid;
    }

    public String Zid;
    public String username;
    public String password;
    private String role;
    private boolean enabled;
    @ElementCollection
    public List<Long> friends;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<Long> getFriends() {
        return friends;
    }

    public void setFriends(long friend) {
        friends.add(friend);
    }

    protected Customer(){}
    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }
}
