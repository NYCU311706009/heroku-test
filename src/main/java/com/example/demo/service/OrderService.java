package com.example.demo.service;

import com.example.demo.Entity.NewOrder;
import com.example.demo.Entity.OrderParams;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void saveOrder(OrderParams orderParams){
        NewOrder order = new NewOrder.Builder()
                .setOilType(orderParams.getOilType())
                .setStartMonth(orderParams.getStartMonth())
                .setEndMonth(orderParams.getEndMonth())
                .setOilVolume(orderParams.getOilVolume())
                .setHandleFee(orderParams.getHandleFee())
                .setOilCost(orderParams.getOilCost())
                .setOwner(orderParams.getOwner()) //customer username
                .build();
        orderRepository.save(order);
    }
}
