package com.example.demo.service;

import com.example.demo.Entity.CustomUser;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean login(Customer customer){
       Customer c = customerRepository.findByUsername(customer.getUsername());
       if(c.getPassword()== passwordEncoder.encode(customer.getPassword())){
           return true;
       }
       return false;
    }
    public Boolean existsByUsername(String username){
        if((customerRepository.findByUsername(username)!=null)){
            return true;
        }
        return false;
    }
    public Boolean regist(Customer customer){
        Customer c1 = customerRepository.findByUsername(customer.getUsername());
        Customer c2 = customerRepository.findByPassword(passwordEncoder.encode(customer.getPassword()));
        if(c1==null&&c2==null){
            Customer c = new Customer(customer.getUsername(),passwordEncoder.encode(customer.getPassword()));
            c.setRole("USER");
            customerRepository.save(c);
            System.out.println(c.getId()+"\n"+c.getUsername()+"\n"+c.getPassword());
            return true;
        }
        return false;
    }
    public Customer findByUsername(String username){
        return customerRepository.findByUsername(username);
    }
    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer c = customerRepository.findByUsername(username);

        CustomUser cu = new CustomUser(c);
        return cu;
    }

}
