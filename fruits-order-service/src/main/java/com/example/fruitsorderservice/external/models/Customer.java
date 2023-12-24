package com.example.fruitsorderservice.external.models;

import lombok.*;

@Builder @AllArgsConstructor
@Getter @Setter
public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

    private Address address;

    public Customer (){}
}
