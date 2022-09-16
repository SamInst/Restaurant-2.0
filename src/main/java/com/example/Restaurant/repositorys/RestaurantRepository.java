package com.example.Restaurant.repositorys;

import com.example.Restaurant.entitys.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {
    List<Restaurant> queryByDeliveryFeeBetween(BigDecimal initialFee, BigDecimal finalFee);
    List<Restaurant> queryByName(String name, @Param("id") Long kitchen);
    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);
    List<Restaurant> findTop2ByNameContaining(String name);
    int countByKitchen_Id(Long kitchen);
}
