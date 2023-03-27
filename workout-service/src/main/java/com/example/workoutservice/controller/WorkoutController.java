package com.example.workoutservice.controller;

import com.example.workoutservice.entity.Workout;
import com.example.workoutservice.exception.WorkoutNotFoundException;
import com.example.workoutservice.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/workout-service")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/default/workouts")
    public ResponseEntity<?> getDefaultWorkouts(){
        return ResponseEntity.ok(workoutService.getDefaultWorkouts());
    }

    @PostMapping("/default/create")
    public ResponseEntity<?> createDefaultWorkout(@Valid @RequestBody Workout workout){
        workoutService.createWorkout(workout, true);
        return ResponseEntity.ok(responseMessage("Default workout successfully created."));
    }

    @PutMapping("/default/update")
    public ResponseEntity<?> updateDefaultWorkout(@Valid @RequestBody Workout workout){
        workoutService.updateWorkout(workout, true);
        return ResponseEntity.ok(responseMessage("Default workout successfully updated."));
    }

    @DeleteMapping("/default/delete")
    public ResponseEntity<?> deleteDefaultWorkout(@RequestParam("id") Long workoutId) throws WorkoutNotFoundException {
        workoutService.deleteWorkout(workoutId, true);
        return ResponseEntity.ok(responseMessage("Default workout successfully deleted."));
    }

    @GetMapping("/custom/workouts")
    public ResponseEntity<?> getCustomWorkouts(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(workoutService.getCustomWorkouts(userId));
    }

    @PostMapping("/custom/create")
    public ResponseEntity<?> createCustomWorkout(@Valid @RequestBody Workout workout){
        workoutService.createWorkout(workout, false);
        return ResponseEntity.ok(responseMessage("Custom workout successfully created."));
    }

    @PutMapping("/custom/update")
    public ResponseEntity<?> updateCustomWorkout(@Valid @RequestBody Workout workout){
        workoutService.updateWorkout(workout, false);
        return ResponseEntity.ok(responseMessage("Custom workout successfully updated."));
    }

    @DeleteMapping("/custom/delete")
    public ResponseEntity<?> deleteCustomWorkout(@RequestParam("id") Long workoutId) throws WorkoutNotFoundException {
        workoutService.deleteWorkout(workoutId, false);
        return ResponseEntity.ok(responseMessage("Custom workout successfully deleted."));
    }

    @GetMapping("/workout")
    public ResponseEntity<?> getWorkout(@RequestParam("id") Long workoutId) throws WorkoutNotFoundException {
        return ResponseEntity.ok(workoutService.getWorkoutById(workoutId));
    }

    private Map<String, String> responseMessage(String message){
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    @ExceptionHandler(value = WorkoutNotFoundException.class)
    public ResponseEntity<?> handleWorkoutNotFoundException(){
        return new ResponseEntity<>("Workout with this id not found", HttpStatus.NOT_FOUND);
    }
}
