package com.example.Restaurant.domain.Exceptions;

public class EntityInUse extends RuntimeException{
    private static final Long serialVersionUID = 1L;
    public EntityInUse(String message){
        super(message);

    }
}
