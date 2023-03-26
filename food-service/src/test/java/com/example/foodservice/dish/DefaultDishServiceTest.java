package com.example.foodservice.dish;

import com.example.foodservice.entity.Category;
import com.example.foodservice.entity.Dish;
import com.example.foodservice.repository.DishRepository;
import com.example.foodservice.service.DefaultDishService;
import com.example.foodservice.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class DefaultDishServiceTest {
    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishService dishService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        dishService = new DefaultDishService(dishRepository);

        Dish dish1 = Dish.builder()
                .id(1l)
                .name("Dish1")
                .category(Category.BEVERAGES)
                .description("Dish1")
                .isDefault(true)
                .creatorId(0l)
                .build();

        Dish dish2 = Dish.builder()
                .id(2l)
                .name("Dish2")
                .category(Category.BEVERAGES)
                .description("Dish2")
                .isDefault(true)
                .creatorId(0l)
                .build();

        Dish dish3 = Dish.builder()
                .id(3l)
                .name("Dish3")
                .category(Category.FRUITS)
                .description("Dish3")
                .isDefault(false)
                .creatorId(1l)
                .build();

        List<Dish> dishes = List.of(dish1, dish2, dish3);

        when(dishRepository.getAllByIsDefaultTrue())
                .thenReturn(dishes.stream().filter(x -> x.getIsDefault()).toList());

        when(dishRepository.getDishesByCategoryAndIsDefaultIsTrue(Category.BEVERAGES))
                .thenReturn(dishes.stream().filter(x -> x.getCategory().equals(Category.BEVERAGES)).toList());

        when(dishRepository.getDishesByCategoryAndIsDefaultIsTrue(Category.FRUITS))
                .thenReturn(dishes.stream().filter(x -> x.getCategory().equals(Category.FRUITS)).toList());

        when(dishRepository.getDishesByCreatorId(1l))
                .thenReturn(dishes.stream().filter(x -> x.getCreatorId() == 1l).toList());

        when(dishRepository.findById(1l)).thenReturn(Optional.of(dish1));
    }
    @Test
    public void getAllDefaultsDishes(){
        List<Dish> dishes = dishService.getDefaultDishes();
        assertEquals(2, dishes.size());
    }

    @Test
    public void getDishesByCategory(){
        List<Dish> dishes = dishService.getDishesByCategory("BVR");
        assertEquals(2, dishes.size());
    }

    @Test
    public void getDishesByCategoryButCategoryCodeIsInvalid(){
        List<Dish> productList = dishService.getDishesByCategory("AAO");
        assertEquals(1, productList.size());
    }

    @Test
    public void getCustomDishes(){
        List<Dish> productList = dishService.getCustomDishes(1l);
        assertEquals(productList.size(), 1);
        assertEquals(Category.FRUITS, productList.stream().findAny().get().getCategory());
        assertEquals("Dish3", productList.stream().findAny().get().getName());
    }

}
