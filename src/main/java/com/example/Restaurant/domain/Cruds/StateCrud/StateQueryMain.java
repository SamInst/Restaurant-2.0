package com.example.Restaurant.domain.Cruds.StateCrud;

import com.example.Restaurant.entitys.State;
import com.example.Restaurant.RestaurantApplication;
import com.example.Restaurant.repositorys.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class StateQueryMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(RestaurantApplication.class)
                .web(WebApplicationType.NONE).run(args);
        StateRepository stateRepository= applicationContext.getBean(StateRepository.class);

        List<State> allStates = stateRepository.all();
        for (State state : allStates){
            System.out.println(state.getName());
        }
    }
}
