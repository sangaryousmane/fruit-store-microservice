package com.ousmane.fruitscustomersservice.repository;

import com.ousmane.fruitscustomersservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, String> {
}
