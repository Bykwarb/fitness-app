package com.example.workoutservice.exception;

public class WorkoutNotFoundException extends Exception{
    public WorkoutNotFoundException(String message) {
        super(message);
    }
}
