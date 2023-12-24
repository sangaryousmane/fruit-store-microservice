package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(int customerId, int fruitId);
    List<Order> getOrdersByCustomer(int customerId);
    List<Order> getAllOrders();
    List<Order> getOrderHistoryForCustomer(int customerId);
    Order getOrderById(String orderId);
    boolean cancelOrder(String orderId);
    Order updateOrder(Order order, String orderId);
}
