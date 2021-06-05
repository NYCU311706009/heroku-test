package com.example.demo.Repository;

import com.example.demo.Entity.NewOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends CrudRepository<NewOrder,Long> {

    List<NewOrder> findByOwner(String Owner);
}
