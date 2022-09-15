package com.example.Restaurant.Spec;

import com.example.Restaurant.Entitys.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {
    public static Specification<Restaurant> withFreeFee(){
        return (root, query, builder) -> builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSameName(String name){
        return (root, query, builder) -> builder.equal(root.get("name"), "%" + name + "%");
    }
}
