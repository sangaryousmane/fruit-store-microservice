package com.ousmane.fruitscustomersservice.service;

import com.ousmane.fruitscustomersservice.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    boolean deleteCustomer(String customerId);
    Customer getCustomerById(String customerId);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Customer customer, String customerId);
}
