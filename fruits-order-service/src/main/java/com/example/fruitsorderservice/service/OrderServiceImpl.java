package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.exceptions.OrderNotFoundException;
import com.example.fruitsorderservice.model.Order;
import com.example.fruitsorderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

    @Override
    public Order placeOrder(int customerId, int fruitId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public List<Order> getOrderHistoryForCustomer(int customerId) {
        return null;
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Override
    public boolean cancelOrder(String orderId) {
        boolean isOrderExist = orderRepo.existsById(orderId);
        if(isOrderExist){
            orderRepo.deleteById(orderId);
            return true;
        }
        return false;
    }

    @Override
    public Order updateOrder(Order order, String orderId) {
        return orderRepo.findById(orderId)
                .map(existingOrder ->{
                    existingOrder.setOrderStatus(order.getOrderStatus());
                    existingOrder.setPaymentType(order.getPaymentType());
                    existingOrder.setCustomerId(order.getCustomerId());
                    existingOrder.setOrderId(order.getOrderId());
                    return orderRepo.save(existingOrder);
                })
                .orElseThrow(() -> new OrderNotFoundException("Sorry, order not found"));
    }
}
