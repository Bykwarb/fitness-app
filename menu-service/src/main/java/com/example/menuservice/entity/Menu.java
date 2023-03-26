package com.example.menuservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    private List<Long> dishes_Id;
    private Double calories;

    private Boolean isDefault;
    private Long creatorId;
}
