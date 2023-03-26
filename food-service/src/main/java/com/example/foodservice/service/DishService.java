package com.example.foodservice.service;

import com.example.foodservice.entity.Dish;
import com.example.foodservice.exception.DishNotFoundedException;

import java.util.List;

public interface DishService {
    List<Dish> getDefaultDishes();
    List<Dish> getDishesByCategory(String categoryCode);
    List<Dish> getCustomDishes(Long userId);
    List<Dish> getCustomDishesByCategory(String categoryCode, Long userId);
    Dish getDishById(Long id) throws DishNotFoundedException;
    void createDish(Dish dish);
    void updateDish(Dish dish);
    void deleteDish(Long dishId);
}
