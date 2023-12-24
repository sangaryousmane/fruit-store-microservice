package com.example.fruitsorderservice.external.models;

import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class Fruit {

    private Long id;
    private String fruitName;
    private String description;
    private int quantity;
    private double price;
}
