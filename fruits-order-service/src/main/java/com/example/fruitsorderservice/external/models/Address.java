package com.example.fruitsorderservice.external.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String postalCode;
    private String city;
}
