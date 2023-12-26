package com.example.fruitsorderservice.dto;

import com.example.fruitsorderservice.model.FruitDetails;
import com.example.fruitsorderservice.model.PaymentType;

import java.util.List;

public class OrderRequest {

    private String customerId;
    private List<FruitDetails> fruits_ordered;

    private PaymentType paymentType;

    public OrderRequest() {}

    public OrderRequest(String customerId, List<FruitDetails> fruitIds) {
        this.customerId = customerId;
        this.fruits_ordered = fruitIds;
    }


    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<FruitDetails> getFruits_ordered() {
        return fruits_ordered;
    }

    public void setFruits_ordered(List<FruitDetails> fruits_ordered) {
        this.fruits_ordered = fruits_ordered;
    }
}
