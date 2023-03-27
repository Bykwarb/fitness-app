package com.example.exerciseservice.repository;

import com.example.exerciseservice.entity.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> getExercisesByIsDefaultIsTrue();

    List<Exercise> getExercisesByIsDefaultIsTrueAndType(Exercise.Type type);

    List<Exercise> getExercisesByCreatorId(Long creatorId);

    List<Exercise> getExercisesByCreatorIdAndType(Long creatorId, Exercise.Type type);
}
