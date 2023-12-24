package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService{


    @Override
    public Order placeOrder(int customerId, int fruitId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {
        return null;
    }
}
