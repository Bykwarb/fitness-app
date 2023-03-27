package com.example.exerciseservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise")
    private String name;

    private String description;

    private String instruction;

    private Type type;

    private Integer sets;

    private Integer reps;

    public enum Type{
        CARDIO,
        POWER,
        STRETCHES
    }
}
