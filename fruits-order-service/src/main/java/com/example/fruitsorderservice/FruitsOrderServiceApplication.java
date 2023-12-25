package com.example.fruitsorderservice;

import com.example.fruitsorderservice.model.Order;
import com.example.fruitsorderservice.model.OrderStatus;
import com.example.fruitsorderservice.model.PaymentType;
import com.example.fruitsorderservice.repository.OrderRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FruitsOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FruitsOrderServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(OrderRepository orderRepo) {
        return (args) -> {
            Order order = Order.builder()
                    .orderId(UUID.randomUUID().toString())
                    .orderStatus(OrderStatus.PAID)
                    .paymentType(PaymentType.ALIPAY)
                    .customerId("021daf47-1514-465a-8e91-0dd5c0f6811d")
                    .fruitIds(List.of(1L, 2L))
                    .build();
            orderRepo.save(order);
        };
    }
}
