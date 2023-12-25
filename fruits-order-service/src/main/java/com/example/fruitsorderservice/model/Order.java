package com.example.fruitsorderservice.model;

import com.example.fruitsorderservice.external.models.Customer;
import com.example.fruitsorderservice.external.models.Fruit;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Order")
@Table(name = "orders",
        uniqueConstraints = @UniqueConstraint(name = "orderUnique",
                columnNames = "orderId"))
public class Order {

    @Id
    private String orderId;
    private Customer customer;
    private List<Fruit> fruits;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerId) {
        this.customer = customerId;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
