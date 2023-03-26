package com.example.foodservice.controller;

import com.example.foodservice.entity.Dish;
import com.example.foodservice.exception.DishNotFoundedException;
import com.example.foodservice.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/food-service/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/default/create")
    public ResponseEntity<?> createDefaultDish(@RequestBody Dish dish){
        dishService.createDish(dish);
        return ResponseEntity.ok(responseMessage("Default dish successfully created"));
    }

    @DeleteMapping("/default/delete")
    public ResponseEntity<?> deleteDefaultDish(@RequestParam("dishId") Long dishId){
        dishService.deleteDish(dishId);
        return ResponseEntity.ok(responseMessage("Default dish successfully deleted"));
    }

    @PutMapping("/default/update")
    public ResponseEntity<?> updateDefaultDish(@RequestBody Dish dish){
        dishService.updateDish(dish);
        return ResponseEntity.ok(responseMessage("Default dish successfully updated"));
    }

    @GetMapping("/default/dishes")
    public ResponseEntity<?> getListDefaultDishes(){
        List<Dish> dishes = dishService.getDefaultDishes();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/default/dishes/category")
    public ResponseEntity<?> getDefaultCategoryDishesList(@RequestParam("code") String categoryCode){
        List<Dish> dishes = dishService.getDishesByCategory(categoryCode);
        return ResponseEntity.ok(dishes);
    }

    @PostMapping("/custom/create")
    public ResponseEntity<?> createCustomDish(@RequestBody Dish dish){
        dishService.createDish(dish);
        return ResponseEntity.ok(responseMessage("Custom dish successfully created"));
    }

    @DeleteMapping("/custom/delete")
    public ResponseEntity<?> deleteCustomDish(@RequestParam("dishId") Long dishId){
        dishService.deleteDish(dishId);
        return ResponseEntity.ok(responseMessage("Custom dish successfully deleted"));
    }

    @PutMapping("/custom/update")
    public ResponseEntity<?> updateCustomDish(@RequestBody Dish dish){
        dishService.updateDish(dish);
        return ResponseEntity.ok(responseMessage("Custom dish successfully updated"));
    }

    @GetMapping("/custom/dishes")
    public ResponseEntity<?> getListCustomDishes(@RequestParam("userId") Long userId){
        List<Dish> dishes = dishService.getCustomDishes(userId);
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/custom/dishes/category")
    public ResponseEntity<?> getCustomCategoryDishesList(@RequestParam("code") String categoryCode,
                                                          @RequestParam("userId") Long userId){
        List<Dish> products = dishService.getCustomDishesByCategory(categoryCode, userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/")
    public ResponseEntity<?> getDish(@RequestParam("dishId") Long dishId) throws DishNotFoundedException {
        return ResponseEntity.ok(dishService.getDishById(dishId));
    }

    @ExceptionHandler(value = DishNotFoundedException.class)
    public ResponseEntity<?> productNotFounded(){
        Dish dish = new Dish();
        dish.setName("Dish with this id not found");
        return ResponseEntity.ok(dish);
    }

    private Map<String, String> responseMessage(String message){
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
