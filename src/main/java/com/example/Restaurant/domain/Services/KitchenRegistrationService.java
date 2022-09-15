package com.example.Restaurant.domain.Services;

import com.example.Restaurant.Entitys.Kitchen;
import com.example.Restaurant.domain.Exceptions.EntityInUse;
import com.example.Restaurant.domain.Exceptions.EntityNotFound;
import com.example.Restaurant.repositorys.KitchenRepository;
import com.example.Restaurant.repositorys.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegistrationService {
    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Kitchen add(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }
    public void exclude(Long kitchenId) {
       try {
           kitchenRepository.deleteById(kitchenId);
       } catch (EmptyResultDataAccessException e){
           throw new EntityNotFound("Kitchen code not found" + kitchenId);
       } catch (DataIntegrityViolationException e) {
           throw new EntityInUse("kitchen code % could be not removed," + kitchenId);
       }
   }
   }
