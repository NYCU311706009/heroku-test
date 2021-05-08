package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByUsername(String username);

    Customer findByPassword(String password);
}
