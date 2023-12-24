package com.ousmane.fruitsconsumerservice.service;

import com.ousmane.fruitsconsumerservice.exceptions.FruitNotFoundException;
import com.ousmane.fruitsconsumerservice.model.Fruit;
import com.ousmane.fruitsconsumerservice.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService{

    private final FruitRepository fruitRepo;

    @Override
    public Fruit saveFruit(Fruit newFruit) {
        if (newFruit != null) {
            log.info("Saving fruit info {}", newFruit);
            return fruitRepo.save(newFruit);
        }
        log.error("Oh Oh! fruit  can't be saved");
        throw new FruitNotFoundException("Sorry, empty fruit can't be saved");
    }

    @Override
    public List<Fruit> getAllFruits() {
        log.warn("you are fetching all fruits from the DB");
        log.info("Retrieving fruits");
        return fruitRepo.findAll();
    }

    @Override
    public Fruit getFruitById(Long fruitId) {
        log.info("Getting fruit by ID: {}", fruitId);
        return fruitRepo.findById(fruitId)
                .orElseThrow(() -> new FruitNotFoundException("fruit not found"));
    }

    @Override
    public Fruit updateFruit(Long fruitId, Fruit fruit) {
        log.info("Updating fruits in DB");
        return fruitRepo.findById(fruitId)
                .map(existingFruit -> {
                    existingFruit.setFruitName(fruit.getFruitName());
                    existingFruit.setDescription(fruit.getDescription());
                    existingFruit.setPrice(fruit.getPrice());
                    existingFruit.setQuantity(fruit.getQuantity());
                    return fruitRepo.save(existingFruit);
                })
                .orElseThrow(() -> new FruitNotFoundException("Sorry, fruit not found"));
    }

    @Override
    public boolean deleteFruit(Long fruitId) {
        log.info("Searching fruit for deletion");
        boolean isFruitExists = fruitRepo.existsById(fruitId);
        if(isFruitExists){
            fruitRepo.deleteById(fruitId);
            log.info("Fruit {} deleted successfully", fruitId);
            return true;
        }
        log.error("Fail to delete fruit");
        return false;
    }

    @Override
    public List<Fruit> searchFruitsByKeyword(String keyword) {
        return fruitRepo.findFruitByFruitNameContainingIgnoreCase(keyword);
    }
}
