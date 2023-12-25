package com.ousmane.fruitsconsumerservice;

import com.github.javafaker.Faker;
import com.ousmane.fruitsconsumerservice.model.Fruit;
import com.ousmane.fruitsconsumerservice.repository.FruitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
public class FruitsConsumerServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(FruitsConsumerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(FruitRepository fruitRepo) {
        return (args) -> {

            Faker faker = new Faker();
            for (int i = 0; i < 200; i++) {
                String fruitName = faker.food().fruit();
                String fruitDescription = fruitName.concat(" is a fruit found ").concat(faker.weather().description());
                Fruit fruit = Fruit.builder()
                        .fruitName(fruitName)
                        .price(Double.parseDouble(faker.commerce().price()))
                        .description(fruitDescription)
                        .quantity(3).build();
                fruitRepo.save(fruit);
            }
        };
    }

}
