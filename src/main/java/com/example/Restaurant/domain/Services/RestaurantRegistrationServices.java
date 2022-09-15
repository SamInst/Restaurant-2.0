package com.example.Restaurant.domain.Services;

import com.example.Restaurant.Entitys.Kitchen;
import com.example.Restaurant.Entitys.Restaurant;
import com.example.Restaurant.domain.Exceptions.EntityInUse;
import com.example.Restaurant.domain.Exceptions.EntityNotFound;
import com.example.Restaurant.repositorys.KitchenRepository;
import com.example.Restaurant.repositorys.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegistrationServices {
@Autowired
private RestaurantRepository restaurantRepository;
@Autowired
private KitchenRepository kitchenRepository;
public Restaurant add(Restaurant restaurant){
    Long kitchenId = restaurant.getKitchen().getId();
    Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(()-> new EntityNotFound(
            String.format("Doens't exists kitchen code registration if code: " + kitchenId)));
    restaurant.setKitchen(kitchen);
    return restaurantRepository.save(restaurant);
}
    public void exclude(Long restaurantId) {
        try {
            restaurantRepository.deleteById(restaurantId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFound("Restaurant code % not found" + restaurantId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUse("Restaurant code % could be not removed," + restaurantId);
        }
    }
}
