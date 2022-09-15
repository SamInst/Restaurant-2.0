package com.example.Restaurant.domain.Services;

import com.example.Restaurant.Entitys.State;
import com.example.Restaurant.domain.Exceptions.EntityInUse;
import com.example.Restaurant.domain.Exceptions.EntityNotFound;
import com.example.Restaurant.repositorys.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateRegistrationService {
    @Autowired
    private StateRepository stateRepository;

    public State add(State state) {
        return stateRepository.add(state);
    }
    public void exclude(Long stateId) {
       try {
           stateRepository.remove(stateId);
       } catch (EmptyResultDataAccessException e){
           throw new EntityNotFound("Kitchen code % not found" + stateId);
       } catch (DataIntegrityViolationException e) {
           throw new EntityInUse("kitchen code % could be not removed," + stateId);
       }
   }
}