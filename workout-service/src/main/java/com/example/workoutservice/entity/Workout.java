package com.example.workoutservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workout_name")
    @NotNull
    private String name;
    private String description;

    @NotNull
    @ElementCollection
    private List<Long> exercisesId;

    @Min(value = 0)
    private Integer caloriesBurned;
    private Boolean isDefault;

    private Long creatorId;
}
