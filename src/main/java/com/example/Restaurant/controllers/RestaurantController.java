package com.example.Restaurant.controllers;

import com.example.Restaurant.entitys.Restaurant;
import com.example.Restaurant.domain.Exceptions.EntityInUse;
import com.example.Restaurant.domain.Exceptions.EntityNotFound;
import com.example.Restaurant.repositorys.RestaurantRepository;
import com.example.Restaurant.domain.Services.RestaurantRegistrationServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantRegistrationServices restaurantRegistrationServices;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> find(@PathVariable("restaurantId") Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegistrationServices.add(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
//        Optional <Restaurant> restaurant1 = restaurantRepository.findById(restaurantId);
        try {
            Restaurant restaurant1 = restaurantRepository.findById(restaurantId).orElse(null);

            if (restaurant1 != null) {
                BeanUtils.copyProperties(restaurant, restaurant1);
                restaurant1 = restaurantRegistrationServices.add(restaurant1);
                return ResponseEntity.ok(restaurant1);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> remove(@PathVariable Long restaurantId) {
        try {
            restaurantRegistrationServices.exclude(restaurantId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUse e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Restaurant restaurant1 = restaurantRepository.findById(restaurantId).orElse(null);

        if (restaurant1 == null) {
            return ResponseEntity.notFound().build();
        }
        merge(fields, restaurant1);
        return update(restaurantId, restaurant1);
    }
    private void merge(Map<String, Object> originData, Restaurant restaurantDestiny) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantOrigin = objectMapper.convertValue(originData, Restaurant.class);

        originData.forEach((nameProperties, valueProperties) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, nameProperties);
            field.setAccessible(true);
            Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
            ReflectionUtils.setField(field, restaurantDestiny, newValue);
        });
    }
}