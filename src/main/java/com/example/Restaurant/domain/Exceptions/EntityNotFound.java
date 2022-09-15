package com.example.Restaurant.domain.Exceptions;

public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String message){
        super(message);

    }

}
