package com.ousmane.fruitscustomersservice.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String country;
    private String postalCode;
    private String city;

    public Address(String country, String postalCode, String city) {
        this.country = country;
        this.postalCode = postalCode;
        this.city = city;
    }
}
