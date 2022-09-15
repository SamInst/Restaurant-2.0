package com.example.Restaurant.repositorys;

import com.example.Restaurant.Entitys.Kitchen;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {
List<Kitchen> findTodasByNameContaining(String name);
Optional<Kitchen> findByName (String name);
boolean existsByName(String name);

}

