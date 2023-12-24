package com.ousmane.fruitsconsumerservice.service;

import com.ousmane.fruitsconsumerservice.model.Fruit;

import java.util.List;

public interface FruitService {

    Fruit saveFruit(Fruit newFruit);
    List<Fruit> getAllFruits();
    Fruit getFruitById(Long id);
    Fruit updateFruit(Long id, Fruit updatedFruit);
    boolean deleteFruit(Long id);
    List<Fruit> searchFruitsByKeyword(String keyword);
}
