package com.ousmane.fruitsconsumerservice.service;

import com.ousmane.fruitsconsumerservice.model.Fruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FruitService {

    Fruit saveFruit(Fruit newFruit);
    List<Fruit> getAllFruits();
    Fruit getFruitById(Long id);
    Fruit updateFruit(Long id, Fruit updatedFruit);
    boolean deleteFruit(Long id);
    List<Fruit> searchFruitsByKeyword(String keyword);
    void updateFruitQuantityInTransaction(Long fruitId, int quantityToAdd);
    void reduceFruitQuantity(Long fruitId, int quantityToAdd);
    Page<Fruit> getFruitsWithFilters(String name, Double minPrice, Double maxPrice, Pageable pageable);
    List<Fruit> getAllFruitsById(List<Long> fruitId);
}
