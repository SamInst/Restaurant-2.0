package com.example.Restaurant.repositorys;

import com.example.Restaurant.Entitys.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
