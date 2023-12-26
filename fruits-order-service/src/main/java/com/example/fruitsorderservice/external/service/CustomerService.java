package com.example.fruitsorderservice.external.service;

import com.example.fruitsorderservice.external.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "FRUITS-CUSTOMER-SERVICE/api/v1/customers")
public interface CustomerService {

    @PostMapping("/saveCustomer")
    Customer saveCustomer(@RequestBody Customer customer);


    @GetMapping("/{customerId}")
    Customer getCustomerById(@PathVariable String customerId);
}
