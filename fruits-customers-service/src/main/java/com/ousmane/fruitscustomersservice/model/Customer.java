package com.ousmane.fruitscustomersservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Customer")
@Table(
        name = "customers", 
        uniqueConstraints = @UniqueConstraint(columnNames = "email",
                name = "uniqueCustomer"))
@Builder
public class Customer {
    
    @Id
    private String customerId = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String email;
    
    @Embedded
    private Address address;
    
    public Customer (){}

    public Customer(String customerId, String firstName, String lastName, String email, Address address) {
        this.customerId =  customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
@Embeddable
@Getter @Setter @NoArgsConstructor
class Address{
    private String country;
    private String postalCode;
    private String city;

    public Address(String country, String postalCode, String city) {
        this.country = country;
        this.postalCode = postalCode;
        this.city = city;
    }
}
