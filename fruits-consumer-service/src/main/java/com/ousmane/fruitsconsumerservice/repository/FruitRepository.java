package com.ousmane.fruitsconsumerservice.repository;

import com.ousmane.fruitsconsumerservice.model.Fruit;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.util.List;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {

    @Query("SELECT fruit FROM Fruit fruit WHERE LOWER(fruit.fruitName)" +
            " LIKE LOWER(CONCAT('%', '?1', '%'))")
    List<Fruit> findFruitByFruitNameContainingIgnoreCase(String keyword);
    Page<Fruit> findByFruitNameContainingAndPriceBetween(String name, Double minPrice, Double maxPrice, Pageable pageable);

}
