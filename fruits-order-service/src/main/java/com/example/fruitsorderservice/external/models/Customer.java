package com.example.fruitsorderservice.external.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder @AllArgsConstructor
@Getter @Setter
public class Customer {

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

    @Embedded
    private Address address;

    public Customer (){}
}
