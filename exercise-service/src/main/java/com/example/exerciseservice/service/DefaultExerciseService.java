package com.example.exerciseservice.service;

import com.example.exerciseservice.entity.Exercise;
import com.example.exerciseservice.exception.ExerciseNotFoundException;
import com.example.exerciseservice.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultExerciseService implements ExerciseService{

    private final ExerciseRepository exerciseRepository;

    public DefaultExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> getDefaultExercises() {
        return exerciseRepository.getExercisesByIsDefaultIsTrue();
    }

    @Override
    public List<Exercise> getDefaultExercisesByType(Exercise.Type type) {
        return exerciseRepository.getExercisesByIsDefaultIsTrueAndType(type);
    }

    @Override
    public List<Exercise> getCustomExercises(Long creatorId) {
        return exerciseRepository.getExercisesByCreatorId(creatorId);
    }

    @Override
    public List<Exercise> getCustomExercisesByType(Long creatorId, Exercise.Type type) {
        return exerciseRepository.getExercisesByCreatorIdAndType(creatorId, type);
    }

    @Override
    public Exercise getExerciseById(Long exerciseId) throws ExerciseNotFoundException {
        return exerciseRepository.findById(exerciseId).orElseThrow(()-> new ExerciseNotFoundException("Exercise with id " + exerciseId + " not founded."));
    }

    @Override
    public void createExercise(Exercise exercise, boolean isDefault) {
        exercise.setIsDefault(isDefault);
        exerciseRepository.save(exercise);
    }

    @Override
    public void deleteExercise(Long exerciseId, boolean isDefault) throws ExerciseNotFoundException {
        if (isDefault){
            exerciseRepository.deleteById(exerciseId);
        }else {
            Exercise exercise = exerciseRepository
                    .findById(exerciseId)
                    .orElseThrow(()-> new ExerciseNotFoundException("Exercise with id " + exerciseId + " not founded."));
            if (!exercise.getIsDefault()){
                exerciseRepository.deleteById(exerciseId);
            }
        }
    }

    @Override
    public void updateExercise(Exercise exercise, boolean isDefault) {
        if (exercise.getIsDefault() == isDefault || isDefault){
            exerciseRepository.save(exercise);
        }
    }
}
