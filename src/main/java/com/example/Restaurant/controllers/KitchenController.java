package com.example.Restaurant.controllers;

import com.example.Restaurant.entitys.Kitchen;
import com.example.Restaurant.domain.Exceptions.EntityInUse;
import com.example.Restaurant.domain.Exceptions.EntityNotFound;
import com.example.Restaurant.repositorys.KitchenRepository;
import com.example.Restaurant.domain.Services.KitchenRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private KitchenRegistrationService kitchenRegistrationService;
    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }
    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> find(@PathVariable("kitchenId") Long id) {
        Optional <Kitchen> kitchen = kitchenRepository.findById(id);
        return ResponseEntity.ok(kitchen.get()); }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return kitchenRegistrationService.add(kitchen);
    }
    
    
    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Optional <Kitchen> kitchen1 = kitchenRepository.findById(kitchenId);

        if (kitchen1.isPresent()) {
            BeanUtils.copyProperties(kitchen, kitchen1.get(), "id"); // for a lot of data in kitchen
            Kitchen kitchenSaved = kitchenRegistrationService.add(kitchen1.get());
            return ResponseEntity.ok(kitchenSaved);
        }
        return ResponseEntity.notFound().build();
        
        
        
    }
    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId) {
        try {
           kitchenRegistrationService.exclude(kitchenId);
           return ResponseEntity.noContent().build();
        } catch (EntityNotFound e) {
            return ResponseEntity.notFound().build();
            } catch (EntityInUse e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}


