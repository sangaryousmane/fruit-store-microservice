package com.ousmane.fruitscustomersservice.service;

import com.ousmane.fruitscustomersservice.exceptions.CustomerNotFoundException;
import com.ousmane.fruitscustomersservice.model.Address;
import com.ousmane.fruitscustomersservice.model.Customer;
import com.ousmane.fruitscustomersservice.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j @RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomersRepository customerRepo;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        boolean isCustomerExist = customerRepo.existsById(customerId);
        if(isCustomerExist){
            customerRepo.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer with ID: "+ customerId+ " not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer, String customerId) {
        return customerRepo.findById(customerId)
                .map(existingCustomer ->{
                    existingCustomer.setCustomerId(customer.getCustomerId());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    if (customer.getAddress() != null){
                        existingCustomer.setAddress(customer.getAddress());
                    }
                    return customerRepo.save(existingCustomer);
                }).orElseThrow(()-> new CustomerNotFoundException("Customer with ID: "+ customerId+ " not found"));
    }
}
