package com.example.fruitsorderservice.repository;
import com.example.fruitsorderservice.external.models.Customer;
import com.example.fruitsorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.customerId=:customerId")
    List<Order> findByCustomer(@Param("customerId") String customerId);
}
