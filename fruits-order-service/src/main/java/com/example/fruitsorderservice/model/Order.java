package com.example.fruitsorderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    private String customerId;


    @ElementCollection
    @CollectionTable(name = "ordered_fruits",
            joinColumns = @JoinColumn(referencedColumnName = "orderId"))
    @Column(name = "fruit_ids")
    private List<Long> fruitIds;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


    public String getOrderId() {
        return orderId;
    }

    public List<Long> getFruitIds() {
        return fruitIds;
    }

    public void setFruitIds(List<Long> fruitId) {
        this.fruitIds = fruitId;
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
