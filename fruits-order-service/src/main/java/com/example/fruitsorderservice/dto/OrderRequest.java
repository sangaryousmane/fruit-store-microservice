package com.example.fruitsorderservice.dto;

import java.util.List;

public class OrderRequest {

    private String customerId;
    private List<Long> fruitIds;


    public OrderRequest() {}

    public OrderRequest(String customerId, List<Long> fruitIds) {
        this.customerId = customerId;
        this.fruitIds = fruitIds;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Long> getFruitIds() {
        return fruitIds;
    }

    public void setFruitIds(List<Long> fruitIds) {
        this.fruitIds = fruitIds;
    }
}
