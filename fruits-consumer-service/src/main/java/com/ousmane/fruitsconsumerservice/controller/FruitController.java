package com.ousmane.fruitsconsumerservice.controller;

import com.ousmane.fruitsconsumerservice.model.Fruit;
import com.ousmane.fruitsconsumerservice.service.FruitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fruits")
@RequiredArgsConstructor
public class FruitController {

    private final FruitServiceImpl fruitService;

    @GetMapping
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return ResponseEntity.ok(fruitService.getAllFruits());
    }

    @GetMapping("/{fruitId}")
    public ResponseEntity<Fruit> getFruitById(
            @PathVariable Integer fruitId) {
        return ResponseEntity.ok(fruitService.getFruitById(fruitId));
    }

    @PostMapping("/saveFruit")
    public ResponseEntity<Fruit> saveFruit(
            @RequestBody Fruit fruit) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fruitService.saveFruit(fruit));
    }

    @PutMapping("/updateFruit/{fruitId}")
    public ResponseEntity<Fruit> updateFruit(
            @RequestBody Fruit fruit,
            @PathVariable Integer fruitId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fruitService.updateFruit(fruitId, fruit));
    }

    @DeleteMapping("/deleteFruit/{fruitId}")
    public ResponseEntity<Map<String, Boolean>> deleteFruit(
            @PathVariable Integer fruitId) {
        boolean isFruitDeleted = fruitService.deleteFruit(fruitId);
        Map<String, Boolean> fruitMap = new HashMap<>();
        fruitMap.put("Deleted", isFruitDeleted);
        return ResponseEntity.status(HttpStatus.OK).body(fruitMap);
    }

    @GetMapping("/searchFruit")
    public ResponseEntity<List<Fruit>> searchFruit(
            @RequestParam String keyword) {
        return ResponseEntity.ok(
                fruitService.searchFruitsByKeyword(keyword));
    }

    @PutMapping("/updateFruitQuantity/{fruitId}")
    public ResponseEntity<String> updateFruitQuantity(
            @PathVariable Integer fruitId,
            @RequestParam int quantity) {
        fruitService.updateFruitQuantityInTransaction(fruitId, quantity);
        return ResponseEntity.ok("Fruit quantity updated");
    }

    @PutMapping("/reduceFruitQuantity/{fruitId}")
    public ResponseEntity<String> reduceFruitQuantity(
            @PathVariable Integer fruitId,
            @RequestParam int quantity) {
        fruitService.reduceFruitQuantity(fruitId, quantity);
        return ResponseEntity.ok("Fruit quantity reduced successfully");
    }

    @GetMapping("/filterFruits")
    public Page<Fruit> getFruits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String fruitName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        Pageable pageable = PageRequest.of(page, size);
        return fruitService.getFruitsWithFilters(fruitName, minPrice, maxPrice, pageable);
    }

    @GetMapping("/allFruits/{fruitId}")
    public ResponseEntity<List<Fruit>> getFruitById(
            @PathVariable List<Integer> fruitId) {
        return ResponseEntity.ok(fruitService.getAllFruitsById(fruitId));
    }
}
