package com.example.fruitsorderservice;

import com.example.fruitsorderservice.model.FruitDetails;
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

import java.time.Instant;
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

            FruitDetails details1 = FruitDetails.builder()
                    .fruitId(2)
                    .fruitName("Mangosteens")
                    .description("Mangosteens is a fruit found Overcast")
                    .price(47.79)
                    .quantity(3)
                    .build();
            FruitDetails details2 = FruitDetails.builder()
                    .fruitId(3)
                    .fruitName("Custard Apples")
                    .description("Custard Apples is a fruit found Mostly cloudy")
                    .price(62.65)
                    .quantity(3)
                    .build();

            Order order = Order.builder()
                    .orderId(UUID.randomUUID().toString())
                    .orderStatus(OrderStatus.SUCCESS)
                    .paymentType(PaymentType.ALIPAY)
                    .customerId("02266b42-7320-4712-8560-8b6acc0579c3")
                    .fruits_ordered(List.of(details1, details2))
                    .orderDate(Instant.now())
                    .build();
            orderRepo.save(order);
        };
    }
}
