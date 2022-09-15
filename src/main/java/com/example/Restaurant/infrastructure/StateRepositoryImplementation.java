package com.example.Restaurant.infrastructure;

import com.example.Restaurant.Entitys.State;
import com.example.Restaurant.repositorys.StateRepository;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class StateRepositoryImplementation implements StateRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<State> all(){
        return manager.createQuery("from State", State.class).getResultList();
    }
    @Override
    public State perId(Long id){
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State add(State state){
        return manager.merge(state);
    }

    @Transactional
    @Override
    public void remove(Long id){
        State state = perId(id);
        manager.remove(state);
    }
}
