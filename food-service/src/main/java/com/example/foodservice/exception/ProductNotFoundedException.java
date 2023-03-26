package com.example.foodservice.exception;

public class ProductNotFoundedException extends Exception{
    public ProductNotFoundedException(String message) {
        super(message);
    }
}
