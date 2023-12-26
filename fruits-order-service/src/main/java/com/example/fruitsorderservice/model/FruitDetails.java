package com.example.fruitsorderservice.model;

import com.example.fruitsorderservice.external.models.Fruit;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Embeddable
@AllArgsConstructor
@Builder
public class FruitDetails {

    private Integer fruitId;
    private String fruitName;
    private String description;
    private int quantity;
    private double price;

    public FruitDetails() {
    }

    public FruitDetails(Fruit fruit) {
        this.fruitId = fruit.getFruitId();
        this.fruitName = fruit.getFruitName();
        this.description = fruit.getDescription();
        this.price = fruit.getPrice();
        this.quantity = fruit.getQuantity();
    }

    public Integer getFruitId() {
        return fruitId;
    }

    public void setFruitId(Integer fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
