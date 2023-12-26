package com.example.fruitsorderservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders",
        uniqueConstraints = @UniqueConstraint(name = "orderUnique",
                columnNames = "orderId"))
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String orderId;

    @CreationTimestamp
    private Instant orderDate;

    private String customerId;


    @ElementCollection
    @CollectionTable(name = "ordered_fruits",
            joinColumns = @JoinColumn(referencedColumnName = "orderId"))
    @Column(name = "fruit_ids")
    @JsonProperty(value = "fruits_ordered")
    private List<FruitDetails> fruits_ordered;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<FruitDetails> getFruits_ordered() {
        return fruits_ordered;
    }

    public void setFruits_ordered(List<FruitDetails> fruitIds) {
        this.fruits_ordered = fruitIds;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

//    public List<Long> setAllFruits(List<Fruit> fruits) {
//        List<Long> fruitList=new ArrayList<>();
//        if (!fruits.isEmpty()){
//           for (Fruit fruit: fruits){
//               fruitList.add(fruit.getFruitId());
//           }
//        }
//        return fruitList;
//
//    }

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
