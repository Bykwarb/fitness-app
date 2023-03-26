package com.example.foodservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "dishes")
public class Dish {

    @Id
    private Long id;

    private String name;

    private Double calories;

    private String description;

    private Category category;

    private Boolean isDefault;

    private Long creatorId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private List<Ingredient> ingredients;

    public Dish() {
    }

    public Dish(Long id, String name, Double calories, String description, Category category, Boolean isDefault, Long creatorId, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.description = description;
        this.category = category;
        this.isDefault = isDefault;
        this.creatorId = creatorId;
        this.ingredients = ingredients;
    }
}
