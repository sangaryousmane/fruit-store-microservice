package com.ousmane.fruitsconsumerservice.model;
import jakarta.persistence.*;
import lombok.Builder;

@Entity(name = "Fruit")
@Table(name = "fruits",
        uniqueConstraints = @UniqueConstraint(name = "fruitUniqueName",
        columnNames = "fruitName"))
@Builder
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fruitName;
    private String description;
    private int quantity;
    private double price;

    public Fruit(){}
    public Fruit(Long id, String fruitName, String description, int quantity, double price) {
        this.id = id;
        this.fruitName = fruitName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
