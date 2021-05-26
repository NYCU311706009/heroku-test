package com.example.demo.Repository;

import com.example.demo.Entity.mOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<mOrder,Long> {

//    @Query("select o From Order o where o.customer = :customer")
//    Order findByCustomer(@Param("customer") Customer customer);
}
