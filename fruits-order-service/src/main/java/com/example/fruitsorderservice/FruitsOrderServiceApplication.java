package com.example.fruitsorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FruitsOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FruitsOrderServiceApplication.class, args);
    }



}
