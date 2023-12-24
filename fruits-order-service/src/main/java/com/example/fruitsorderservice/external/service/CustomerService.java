package com.example.fruitsorderservice.external.service;

import com.example.fruitsorderservice.external.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "FRUITS-CUSTOMER-SERVICE")
public interface CustomerService {

    @PostMapping
    Customer saveCustomer(@RequestBody Customer customer);
}
