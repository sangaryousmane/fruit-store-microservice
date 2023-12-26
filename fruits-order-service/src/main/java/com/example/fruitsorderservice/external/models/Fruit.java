package com.example.fruitsorderservice.external.models;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class Fruit {

    private Integer fruitId;
    private String fruitName;
    private String description;
    private int quantity;
    private double price;
}
