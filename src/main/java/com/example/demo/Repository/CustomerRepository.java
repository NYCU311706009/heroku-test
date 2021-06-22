package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByUsername(String username);

    Customer findByPassword(String password);

    Customer findByEmail(String email);
}
