package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query("select o From Order o where o.customer = :customer")
    Order findByCustomer(@Param("customer") Customer customer);
}
