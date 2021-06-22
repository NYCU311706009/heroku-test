package com.example.demo.service;

import com.example.demo.Entity.CurrentTime;
import com.example.demo.Entity.NewOrder;
import com.example.demo.Entity.OrderParams;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void saveOrder(OrderParams orderParams){
        CurrentTime currentTime = new CurrentTime();

        NewOrder order = new NewOrder.Builder()
                .setOilType(orderParams.getOilType())
                .setStartMonth(orderParams.getStartMonth())
                .setEndMonth(orderParams.getEndMonth())
                .setOilVolume(orderParams.getOilVolume())
                .setHandleFee(orderParams.getHandleFee())
                .setOilCost(orderParams.getOilCost())
                .setOwner(orderParams.getOwner()) //customer username
                .setCardNumber(orderParams.getCreditCardParams().getCardNumber())
                .setTimestamp(currentTime.getTime())
                .build();
        orderRepository.save(order);
    }
}
