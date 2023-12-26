package com.example.fruitsorderservice.external.service;

import com.example.fruitsorderservice.external.models.Fruit;
import com.example.fruitsorderservice.model.FruitDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FRUIT-CONSUMER-SERVICE/api/v1/fruits")
public interface FruitService {

    @GetMapping("/allFruits/{fruitId}")
     List<Fruit> getAllFruitById(
            @PathVariable List<Integer> fruitId);
}
