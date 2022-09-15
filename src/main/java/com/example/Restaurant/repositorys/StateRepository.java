package com.example.Restaurant.repositorys;

import com.example.Restaurant.Entitys.State;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface StateRepository {
    List<State> all();
    State perId(Long id);
    State add(State state);
    void remove(Long id);
}
