package com.example.workoutservice.service;

import com.example.workoutservice.entity.Workout;
import com.example.workoutservice.exception.WorkoutNotFoundException;

import java.util.List;

public interface WorkoutService {

    List<Workout> getDefaultWorkouts();

    List<Workout> getCustomWorkouts(Long creatorId);

    Workout getWorkoutById(Long workoutId) throws WorkoutNotFoundException;

    void updateWorkout(Workout workout, boolean isDefault);

    void createWorkout(Workout workout, boolean isDefault);

    void deleteWorkout(Long workoutId, boolean isDefault) throws WorkoutNotFoundException;
}
