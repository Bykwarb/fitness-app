package com.example.foodservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Category category;

    private Double caloriesPer100g;

    private Double proteinPer100g;

    private Double fatPer100g;

    private Double carbohydratePer100g;

    private Boolean isDefault;

    private Long creatorId;

    public Product() {

    }

    public Product(
            Long id, String name, Category category,
            Double caloriesPer100g, Double proteinPer100g, Double fatPer100g,
            Double carbohydratePer100g, Boolean isDefault, Long creatorId) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.caloriesPer100g = caloriesPer100g;
        this.proteinPer100g = proteinPer100g;
        this.fatPer100g = fatPer100g;
        this.carbohydratePer100g = carbohydratePer100g;
        this.isDefault = isDefault;
        this.creatorId = creatorId;
    }
}
