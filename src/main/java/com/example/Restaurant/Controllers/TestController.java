package com.example.Restaurant.Controllers;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import com.example.Restaurant.entitys.Kitchen;
import com.example.Restaurant.entitys.Restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Restaurant.repositorys.KitchenRepository;
import com.example.Restaurant.repositorys.RestaurantRepository;
@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @GetMapping("/kitchens/unique-by-name")
    public Optional<Kitchen> kitchenByUniqueName(String name) { return kitchenRepository.findByName(name); }
    @GetMapping("/kitchens/by-name")
    public List<Kitchen> kitchensByName(String name) { return kitchenRepository.findTodasByNameContaining(name); }
    @GetMapping("/restaurants/top2-byName")
    public List<Restaurant> restaurantByTopsDeliveryFee(String name) { return restaurantRepository.findTop2ByNameContaining(name); }
    @GetMapping("/restaurants/first-by-name")
    public Optional<Restaurant> restaurantByFirstName(String name ) {return restaurantRepository.findFirstRestaurantByNameContaining (name); }
    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantByName(String name, Long kitchen) {return restaurantRepository.queryByName(name, kitchen); }
    @GetMapping("/restaurants/by-delivery-fee")
    public List<Restaurant> restaurantByDeliveryFee(BigDecimal initialFee, BigDecimal finalFee) { return restaurantRepository.queryByDeliveryFeeBetween(initialFee,finalFee); }
    @GetMapping("/kitchens/exists")
    public boolean kitchenExists(String name) { return kitchenRepository.existsByName(name); }
    @GetMapping("/restaurants/count-by")
    public int restaurantsCountBy(Long kitchenId) { return restaurantRepository.countByKitchen_Id(kitchenId); }
    @GetMapping("/restaurants/by-name-and-fee")
    public List<Restaurant> restaurantByNameAndFee(String name, BigDecimal initialFee, BigDecimal finalFee) { return restaurantRepository.find(name, initialFee, finalFee);}
    @GetMapping("/restaurants/with-free-fee")
    public List<Restaurant> restaurantsWithFreeFee(String name) { return  restaurantRepository.findWithDeliveryFree(name);}
    @GetMapping("/restaurants/first")
    public Optional<Restaurant> firstRestaurant(){
        return restaurantRepository.findFirst();
    }
    @GetMapping("/kitchens/first")
    public Optional<Kitchen> firstKitchen(){
        return kitchenRepository.findFirst();
    }

}