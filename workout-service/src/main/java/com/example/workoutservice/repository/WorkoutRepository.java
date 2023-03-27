package com.example.workoutservice.repository;

import com.example.workoutservice.entity.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    List<Workout> getWorkoutsByIsDefaultIsTrue();

    List<Workout> getWorkoutsByCreatorId(Long creatorId);


}
