package com.ousmane.fruitscustomersservice;

import com.github.javafaker.Faker;
import com.ousmane.fruitscustomersservice.model.Address;
import com.ousmane.fruitscustomersservice.model.Customer;
import com.ousmane.fruitscustomersservice.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class FruitsCustomersServiceApplication {

    private final CustomersRepository customerRepo;

    public static void main(String[] args) {
        SpringApplication.run(FruitsCustomersServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Customer atalib = Customer.builder()
                    .customerId(UUID.randomUUID().toString())
                    .firstName("Atalib")
                    .lastName("Almousleck")
                    .email("atalib@yahoo.com")
                    .address(new Address("China",
                            "40001", "Xian"))
                    .build();
            Customer ousmane = Customer.builder()
                    .customerId(UUID.randomUUID().toString())
                    .firstName("Ousmane")
                    .lastName("Sangary")
                    .email("sangary7683@yahoo.com")
                    .address(new Address("China",
                            "300000", "Wuhan"))
                    .build();
            customerRepo.saveAll(List.of(ousmane, atalib));

            Faker faker=new Faker();

            for (int i=0; i < 200; i++){
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = firstName.concat(".").concat(lastName).concat("@gmail.com");
                Customer customer=Customer.builder()
                        .customerId(UUID.randomUUID().toString())
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email.toLowerCase(Locale.ENGLISH))
                        .address(new Address(
                                faker.address().country(),
                                faker.address().zipCode(),
                                faker.address().cityName()))
                        .build();
                customerRepo.save(customer);
            }
        };
    }

}
