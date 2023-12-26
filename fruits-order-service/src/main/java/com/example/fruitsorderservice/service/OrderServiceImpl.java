package com.example.fruitsorderservice.service;

import com.example.fruitsorderservice.exceptions.OrderNotFoundException;
import com.example.fruitsorderservice.external.models.Customer;
import com.example.fruitsorderservice.external.models.Fruit;
import com.example.fruitsorderservice.external.service.CustomerService;
import com.example.fruitsorderservice.external.service.FruitService;
import com.example.fruitsorderservice.model.FruitDetails;
import com.example.fruitsorderservice.model.Order;
import com.example.fruitsorderservice.model.OrderStatus;
import com.example.fruitsorderservice.model.PaymentType;
import com.example.fruitsorderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    public Order placeOrder(String customerId, List<Integer> fruitIds,
                            PaymentType paymentType) {

        if (fruitIds == null || customerId == null){
            throw new OrderNotFoundException("Sorry, can't process null order");
        }
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {
            log.info("Retrieving fruits from fruit service");
            List<Fruit> fruits = fruitService.getAllFruitById(fruitIds);
            List<FruitDetails> fruitDetails =
                    fruits.stream().map(FruitDetails::new).toList();


            if (!fruits.isEmpty()) {
                Order newOrder = new Order();
                newOrder.setCustomerId(customer.getCustomerId());
                newOrder.setFruits_ordered(fruitDetails);
                newOrder.setOrderStatus(OrderStatus.PENDING);
                log.info("PAYMENT PENDING.....");
                newOrder.setPaymentType(paymentType);
                newOrder.setOrderId(UUID.randomUUID().toString());
                log.info("Order saved successfully with ID: {}", newOrder.getOrderId());
                newOrder.setOrderDate(Instant.now());
                newOrder.setOrderStatus(OrderStatus.SUCCESS);
                log.info("PAYMENT DONE SUCCESSFULLY...");
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

        List<Order> orders = orderRepo.findAll();
        Order order = new Order();
        order.setFruits_ordered(order.getFruits_ordered());
        orders.add(order);
        return orders;
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
        log.info("Cancelling order::{}", orderId);
        if (isOrderExist) {
            orderRepo.deleteById(orderId);
            log.info("Order cancellation successful");
            return true;
        }
        log.error("Order cancellation failed");
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
