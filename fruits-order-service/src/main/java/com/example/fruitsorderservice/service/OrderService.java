package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.model.Order;

import java.util.List;

public interface FruitService {

    Order placeOrder(int customerId, int fruitId);
    List<Order> getOrdersByCustomer(int customerId);
    List<Order> getAllOrders();
    List<Order> getOrderHistoryForCustomer(int customerId);
}
