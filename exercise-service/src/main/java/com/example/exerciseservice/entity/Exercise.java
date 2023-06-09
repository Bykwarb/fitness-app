package com.example.exerciseservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "exercise")
    private String name;

    private String description;

    private String instruction;

    @NotNull
    private Type type;

    @Min(1)
    private Integer sets;

    @Min(1)
    private Integer reps;

    private Boolean isDefault;

    private Long creatorId;

    public enum Type{
        CARDIO,
        POWER,
        STRETCHES
    }
}
