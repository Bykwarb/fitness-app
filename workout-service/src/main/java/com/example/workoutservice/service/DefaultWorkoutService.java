package com.example.workoutservice.service;

import com.example.workoutservice.entity.Workout;
import com.example.workoutservice.exception.WorkoutNotFoundException;
import com.example.workoutservice.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultWorkoutService implements WorkoutService{

    private final WorkoutRepository workoutRepository;

    public DefaultWorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public List<Workout> getDefaultWorkouts() {
        return workoutRepository.getWorkoutsByIsDefaultIsTrue();
    }

    @Override
    public List<Workout> getCustomWorkouts(Long creatorId) {
        return workoutRepository.getWorkoutsByCreatorId(creatorId);
    }

    @Override
    public Workout getWorkoutById(Long workoutId) throws WorkoutNotFoundException {
        return workoutRepository.findById(workoutId).orElseThrow(()-> new WorkoutNotFoundException("Workout with id " + workoutId + " not found."));
    }

    @Override
    public void updateWorkout(Workout workout, boolean isDefault) {
        if (workout.getIsDefault() == isDefault || isDefault){
            workoutRepository.save(workout);
        }
    }

    @Override
    public void createWorkout(Workout workout, boolean isDefault) {
        workout.setIsDefault(isDefault);
        workoutRepository.save(workout);
    }

    @Override
    public void deleteWorkout(Long workoutId, boolean isDefault) throws WorkoutNotFoundException {
        if (isDefault){
            workoutRepository.deleteById(workoutId);
        }else {
            Workout workout = workoutRepository
                    .findById(workoutId)
                    .orElseThrow(()-> new WorkoutNotFoundException("Workout with id " + workoutId + " not found."));
            if (!workout.getIsDefault()){
                workoutRepository.deleteById(workoutId);
            }
        }
    }
}
