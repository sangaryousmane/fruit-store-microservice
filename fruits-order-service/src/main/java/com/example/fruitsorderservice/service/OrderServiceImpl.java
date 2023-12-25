package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.exceptions.OrderNotFoundException;
import com.example.fruitsorderservice.external.models.Customer;
import com.example.fruitsorderservice.external.models.Fruit;
import com.example.fruitsorderservice.external.service.CustomerService;
import com.example.fruitsorderservice.external.service.FruitService;
import com.example.fruitsorderservice.model.Order;
import com.example.fruitsorderservice.model.OrderStatus;
import com.example.fruitsorderservice.model.PaymentType;
import com.example.fruitsorderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final CustomerService customerService;
    private final FruitService fruitService;

    @Override
    public Order placeOrder(String customerId, List<Long> fruitIds) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {
            List<Fruit> fruits = fruitService.getAllFruitById(fruitIds);
            List<Long> fruitId = new ArrayList<>();
            fruits.forEach(f -> fruitId.add(f.getFruitId()));
            if (!fruits.isEmpty()) {
                Order newOrder = new Order();
                newOrder.setCustomerId(customer.getCustomerId());
                newOrder.setFruitIds(fruitId);
                newOrder.setOrderStatus(OrderStatus.PAID);
                newOrder.setPaymentType(PaymentType.CREDIT_CARD);
                newOrder.setOrderId(UUID.randomUUID().toString());
                log.info("Order saved successfully with ID: {}", newOrder.getOrderId());
                return orderRepo.save(newOrder);
            }
        }
        log.error("Transaction failed!! {}", OrderStatus.FAILED);
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomer(String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null)
            return orderRepo.findByCustomer(customer.getCustomerId());
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("Retrieving all orders ");
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getOrderHistoryForCustomer(String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return orderRepo.findByCustomer(customer.getCustomerId());
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Override
    public boolean cancelOrder(String orderId) {
        boolean isOrderExist = orderRepo.existsById(orderId);
        if (isOrderExist) {
            orderRepo.deleteById(orderId);
            return true;
        }
        return false;
    }

    @Override
    public Order updateOrder(Order order, String orderId) {
        return orderRepo.findById(orderId)
                .map(existingOrder -> {
                    existingOrder.setOrderStatus(order.getOrderStatus());
                    existingOrder.setPaymentType(order.getPaymentType());
                    existingOrder.setCustomerId(order.getCustomerId());
                    existingOrder.setOrderId(order.getOrderId());
                    return orderRepo.save(existingOrder);
                })
                .orElseThrow(() -> new OrderNotFoundException("Sorry, order not found"));
    }
}
