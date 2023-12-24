package com.ousmane.fruitserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FruitServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FruitServiceRegistryApplication.class, args);
    }

}
