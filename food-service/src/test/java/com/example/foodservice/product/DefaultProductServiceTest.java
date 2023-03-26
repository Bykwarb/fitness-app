package com.example.foodservice.product;

import com.example.foodservice.entity.Category;
import com.example.foodservice.entity.Product;
import com.example.foodservice.repository.ProductRepository;
import com.example.foodservice.service.DefaultProductService;
import com.example.foodservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class DefaultProductServiceTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        productService = new DefaultProductService(productRepository);
        Product product = new Product();
        product.setId(1l);
        product.setName("BasicFood1");
        product.setCategory(Category.FRUITS);
        product.setCaloriesPer100g(100.0);
        product.setFatPer100g(40.0);
        product.setCarbohydratePer100g(20.0);
        product.setProteinPer100g(10.0);
        product.setIsDefault(true);
        product.setCreatorId(0l);

        Product product2 = new Product();
        product2.setId(2l);
        product2.setName("BasicFood2");
        product2.setCategory(Category.OILS_AND_FATS);
        product2.setCaloriesPer100g(100.0);
        product2.setFatPer100g(40.0);
        product2.setCarbohydratePer100g(20.0);
        product2.setProteinPer100g(10.0);
        product2.setIsDefault(true);
        product2.setCreatorId(0l);

        Product product3 = new Product();
        product3.setId(3l);
        product3.setName("BasicFood3");
        product3.setCategory(Category.FRUITS);
        product3.setCaloriesPer100g(100.0);
        product3.setFatPer100g(40.0);
        product3.setCarbohydratePer100g(20.0);
        product3.setProteinPer100g(10.0);
        product3.setIsDefault(false);
        product3.setCreatorId(1l);

        List<Product> productList = List.of(product, product2, product3);

        when(productRepository.getAllByIsDefaultTrue())
                .thenReturn(productList.stream().filter(x -> x.getIsDefault()).toList());

        when(productRepository.getProductsByCategoryAndIsDefaultTrue(Category.OILS_AND_FATS))
                .thenReturn(productList.stream().filter(x -> x.getCategory().equals(Category.OILS_AND_FATS)).toList());

        when(productRepository.getProductsByCategoryAndIsDefaultTrue(Category.FRUITS))
                .thenReturn(productList.stream().filter(x -> x.getCategory().equals(Category.FRUITS)).toList());

        when(productRepository.getProductsByCreatorId(1l))
                .thenReturn(productList.stream().filter(x -> x.getCreatorId() == 1l).toList());

        when(productRepository.findById(1l)).thenReturn(Optional.of(product));
    }

    @Test
    public void getAllDefaultsProducts(){
        List<Product> productList = productService.getDefaultProducts();
        assertEquals(2, productList.size());
    }

    @Test
    public void getProductByCategory(){
        List<Product> productList = productService.getProductsByCategory("OF");
        assertEquals(1, productList.size());
    }

    @Test
    public void getProductByCategoryButCategoryCodeIsInvalid(){
        List<Product> productList = productService.getProductsByCategory("AAO");
        assertEquals(2, productList.size());
    }

    @Test
    public void getCustomProduct(){
        List<Product> productList = productService.getCustomProducts(1l);
        assertEquals(productList.size(), 1);
        assertEquals(Category.FRUITS, productList.stream().findAny().get().getCategory());
        assertEquals("BasicFood3", productList.stream().findAny().get().getName());
    }
}
