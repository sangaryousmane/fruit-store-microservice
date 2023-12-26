package com.ousmane.fruitsconsumerservice.service;

import com.ousmane.fruitsconsumerservice.model.Fruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FruitService {

    Fruit saveFruit(Fruit newFruit);
    List<Fruit> getAllFruits();
    Fruit getFruitById(Integer id);
    Fruit updateFruit(Integer id, Fruit updatedFruit);
    boolean deleteFruit(Integer id);
    List<Fruit> searchFruitsByKeyword(String keyword);
    void updateFruitQuantityInTransaction(Integer fruitId, int quantityToAdd);
    void reduceFruitQuantity(Integer fruitId, int quantityToAdd);
    Page<Fruit> getFruitsWithFilters(String name, Double minPrice, Double maxPrice, Pageable pageable);
    List<Fruit> getAllFruitsById(List<Integer> fruitId);
}
