package com.ousmane.fruitscustomersservice.controller;

import com.ousmane.fruitscustomersservice.model.Customer;
import com.ousmane.fruitscustomersservice.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(
                customerService.saveCustomer(customer),
                HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestBody Customer customer, String customerId) throws URISyntaxException {
        Customer updatedCustomer = customerService.updateCustomer(customer, customerId);
        return ResponseEntity.created(new URI("/api/v1/customers/updateCustomer/{customerId}"))
                .body(updatedCustomer);
    }
}
