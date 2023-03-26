package com.example.foodservice.repository;

import com.example.foodservice.entity.Category;
import com.example.foodservice.entity.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Dish> getAllByIsDefaultTrue();

    List<Dish> getDishesByCreatorId(Long userId);

    List<Dish> getDishesByCategoryAndIsDefaultIsTrue(Category category);

    List<Dish> getDishesByCategoryAndCreatorId(Category category, Long userId);

}
