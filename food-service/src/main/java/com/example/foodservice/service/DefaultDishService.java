package com.example.foodservice.service;

import com.example.foodservice.entity.Category;
import com.example.foodservice.entity.Dish;
import com.example.foodservice.exception.DishNotFoundedException;
import com.example.foodservice.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultDishService implements DishService{

    private final DishRepository dishRepository;

    public DefaultDishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getDefaultDishes() {
        return dishRepository.getAllByIsDefaultTrue();
    }

    @Override
    public List<Dish> getDishesByCategory(String categoryCode) {
        Category category;
        try{
            category = Category.getByCode(categoryCode);
        }catch (IllegalArgumentException e){
            category = Category.FRUITS;
        }
        return dishRepository.getDishesByCategoryAndIsDefaultIsTrue(category);
    }

    @Override
    public List<Dish> getCustomDishes(Long userId) {

        return dishRepository.getDishesByCreatorId(userId);
    }

    @Override
    public List<Dish> getCustomDishesByCategory(String categoryCode, Long userId) {
        Category category;
        try{
            category = Category.getByCode(categoryCode);
        }catch (IllegalArgumentException e){
            category = Category.FRUITS;
        }
        return dishRepository.getDishesByCategoryAndCreatorId(category, userId);
    }

    @Override
    public Dish getDishById(Long id) throws DishNotFoundedException {
        return dishRepository.findById(id).orElseThrow(()-> new DishNotFoundedException("Dish with id " + id + " not founded."));
    }

    @Override
    public void createDish(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long dishId) {
        dishRepository.deleteById(dishId);
    }
}
