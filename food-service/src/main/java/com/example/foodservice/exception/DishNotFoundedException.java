package com.example.foodservice.exception;

public class DishNotFoundedException extends Exception{
    public DishNotFoundedException(String message) {
        super(message);
    }
}
