package com.example.foodservice.service;

import com.example.foodservice.entity.Category;
import com.example.foodservice.entity.Product;
import com.example.foodservice.exception.ProductNotFoundedException;
import com.example.foodservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService{

    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getDefaultProducts() {
        return productRepository.getAllByIsDefaultTrue();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryCode) {
        Category category;
        try{
            category = Category.getByCode(categoryCode);
        }catch (IllegalArgumentException e){
            category = Category.FRUITS;
        }
        return productRepository.getProductsByCategoryAndIsDefaultTrue(category);
    }

    @Override
    public List<Product> getCustomProducts(Long userId) {
        return productRepository.getProductsByCreatorId(userId);
    }

    @Override
    public List<Product> getCustomProductsByCategory(String categoryCode, Long userId) {
        Category category;
        try{
            category = Category.getByCode(categoryCode);
        }catch (IllegalArgumentException e){
            category = Category.FRUITS;
        }
        return productRepository.getProductsByCategoryAndCreatorId(category, userId);
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundedException {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundedException("Product with id " + id + " not founded."));
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
