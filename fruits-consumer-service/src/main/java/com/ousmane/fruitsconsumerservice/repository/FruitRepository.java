package com.ousmane.fruitsconsumerservice.repository;

import com.ousmane.fruitsconsumerservice.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
}
