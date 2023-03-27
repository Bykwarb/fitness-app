package com.example.exerciseservice.controller;

import com.example.exerciseservice.entity.Exercise;
import com.example.exerciseservice.exception.ExerciseNotFoundException;
import com.example.exerciseservice.service.ExerciseService;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/exercise-service")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/default/exercises")
    public ResponseEntity<?> getDefaultExercises(){
        return ResponseEntity.ok(exerciseService.getDefaultExercises());
    }

    @GetMapping("/default/exercises/type")
    public ResponseEntity<?> getDefaultExercisesByType(@Valid @RequestParam("type")Exercise.Type type){
        return ResponseEntity.ok(exerciseService.getDefaultExercisesByType(type));
    }

    @PostMapping("/default/create")
    public ResponseEntity<?> createDefaultExercise(@Valid @RequestBody Exercise exercise){
        exerciseService.createExercise(exercise, true);
        return ResponseEntity.ok(responseMessage("Default exercise successfully created."));
    }

    @DeleteMapping("/default/delete")
    public ResponseEntity<?> deleteDefaultExercise(@RequestParam("exerciseId")Long exerciseId) throws ExerciseNotFoundException {
        exerciseService.deleteExercise(exerciseId, true);
        return ResponseEntity.ok(responseMessage("Exercise successfully created."));
    }

    @PutMapping("/default/update")
    public ResponseEntity<?> updateDefaultExercise(@Valid @RequestBody Exercise exercise){
        exerciseService.updateExercise(exercise, true);
        return ResponseEntity.ok(responseMessage("Default exercise successfully updated."));
    }

    @GetMapping("/custom/exercises")
    public ResponseEntity<?> getCustomExercises(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(exerciseService.getCustomExercises(userId));
    }

    @GetMapping("/custom/exercises/type")
    public ResponseEntity<?> getCustomExercisesByType(@Valid @RequestParam("type")Exercise.Type type, @RequestParam("userId") Long userId){
        return ResponseEntity.ok(exerciseService.getCustomExercisesByType(userId, type));
    }

    @PostMapping("/custom/create")
    public ResponseEntity<?> createCustomExercise(@Valid @RequestBody Exercise exercise){
        exerciseService.createExercise(exercise, false);
        return ResponseEntity.ok(responseMessage("Custom exercise successfully created."));
    }

    @DeleteMapping("/custom/delete")
    public ResponseEntity<?> deleteCustomExercise(@RequestParam("exerciseId")Long exerciseId) throws ExerciseNotFoundException {
        exerciseService.deleteExercise(exerciseId, false);
        return ResponseEntity.ok(responseMessage("Exercise successfully created."));
    }

    @PutMapping("/custom/update")
    public ResponseEntity<?> updateCustomExercise(@Valid @RequestBody Exercise exercise){
        exerciseService.updateExercise(exercise, true);
        return ResponseEntity.ok(responseMessage("Custom exercise successfully updated."));
    }

    @GetMapping("/exercise")
    public ResponseEntity<?> getExercise(@RequestParam("exerciseId") Long exerciseId) throws ExerciseNotFoundException {
        return ResponseEntity.ok(exerciseService.getExerciseById(exerciseId));
    }

    private Map<String, String> responseMessage(String message){
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    @ExceptionHandler(value = ExerciseNotFoundException.class)
    public ResponseEntity<?> handleExerciseNotFoundException(){
        return new ResponseEntity<>("Exercise with this id not found", HttpStatus.NOT_FOUND);
    }
}
