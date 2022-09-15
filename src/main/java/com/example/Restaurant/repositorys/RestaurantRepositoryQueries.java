package com.example.Restaurant.repositorys;

import com.example.Restaurant.Entitys.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal initialFee, BigDecimal finalFee);
    List<Restaurant> findWithDeliveryFree(String name);
}
