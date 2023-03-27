package com.example.exerciseservice.service;

import com.example.exerciseservice.entity.Exercise;
import com.example.exerciseservice.exception.ExerciseNotFoundException;

import java.util.List;

public interface ExerciseService {

    List<Exercise> getDefaultExercises();

    List<Exercise> getDefaultExercisesByType(Exercise.Type type);

    List<Exercise> getCustomExercises(Long creatorId);

    List<Exercise> getCustomExercisesByType(Long creatorId, Exercise.Type type);

    Exercise getExerciseById(Long exerciseId) throws ExerciseNotFoundException;

    void createExercise(Exercise exercise, boolean isDefault);

    void deleteExercise(Long exerciseId, boolean isDefault) throws ExerciseNotFoundException;

    void updateExercise(Exercise exercise, boolean isDefault);


}
