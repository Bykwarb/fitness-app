package com.example.foodservice.service;

import com.example.foodservice.entity.Product;
import com.example.foodservice.exception.ProductNotFoundedException;

import java.util.List;

public interface ProductService {
    List<Product> getDefaultProducts();
    List<Product> getProductsByCategory(String categoryCode);
    List<Product> getCustomProducts(Long userId);
    List<Product> getCustomProductsByCategory(String categoryCode, Long userId);
    Product getProductById(Long id) throws ProductNotFoundedException;
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long productId);
}
