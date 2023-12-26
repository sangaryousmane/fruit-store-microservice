package com.ousmane.fruitsconsumerservice.service;

import com.ousmane.fruitsconsumerservice.exceptions.FruitNotFoundException;
import com.ousmane.fruitsconsumerservice.exceptions.InsufficientFruitInStore;
import com.ousmane.fruitsconsumerservice.model.Fruit;
import com.ousmane.fruitsconsumerservice.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService {

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
    public Fruit getFruitById(Integer fruitId) {
        log.info("Getting fruit by ID: {}", fruitId);
        return fruitRepo.findById(fruitId)
                .orElseThrow(() -> new FruitNotFoundException("fruit not found"));
    }


    @Override
    public Fruit updateFruit(Integer fruitId, Fruit fruit) {
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
    public boolean deleteFruit(Integer fruitId) {
        log.info("Searching fruit for deletion");
        boolean isFruitExists = fruitRepo.existsById(fruitId);
        if (isFruitExists) {
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


    @Transactional
    @Override
    public void updateFruitQuantityInTransaction(Integer fruitId, int quantityToAdd) {
        Fruit fruit = getFruitById(fruitId);

        if (fruit != null) {
            log.info("Updating fruit quantity {}...", fruit.getQuantity());
            int currentQuantity = fruit.getQuantity();
            fruit.setQuantity(quantityToAdd + currentQuantity);
            fruitRepo.save(fruit);
            log.info("Updated quantity {} successfully...", quantityToAdd);
        }
    }


    @Transactional
    @Override
    public void reduceFruitQuantity(Integer fruitId, int quantityToAdd) {
        Fruit fruit = getFruitById(fruitId);

        if (fruit != null) {
            int currentQuantity = fruit.getQuantity();
            log.info("Reducing fruit quantity...");
            if (quantityToAdd < currentQuantity) {
                fruit.setQuantity(currentQuantity - quantityToAdd);
                fruitRepo.save(fruit);
                log.info("Reduced successfully...");
            } else {
                log.error("Unable to reduce quantity");
                throw new InsufficientFruitInStore("Sorry, fruit in-store is insufficient");
            }
        }
    }

    @Override
    public Page<Fruit> getFruitsWithFilters(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        return fruitRepo.findByFruitNameContainingAndPriceBetween(name, minPrice, maxPrice, pageable);
    }

    @Override
    public List<Fruit> getAllFruitsById(List<Integer> fruitId) {
        log.info("Retrieving all fruits by Id");
        return fruitRepo.findAllById(fruitId);
    }


}
