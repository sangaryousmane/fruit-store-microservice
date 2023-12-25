package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(String customerId, List<Long> fruitId);
    List<Order> getOrdersByCustomer(String customerId);
    List<Order> getAllOrders();
    List<Order> getOrderHistoryForCustomer(String customerId);
    Order getOrderById(String orderId);
    boolean cancelOrder(String orderId);
    Order updateOrder(Order order, String orderId);
}
