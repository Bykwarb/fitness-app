package com.example.foodservice.repository;

import com.example.foodservice.entity.Category;
import com.example.foodservice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> getAllByIsDefaultTrue();
    List<Product> getProductsByCreatorId(Long creatorId);
    List<Product> getProductsByCategoryAndIsDefaultTrue(Category category);
    List<Product> getProductsByCategoryAndCreatorId(Category category, Long creatorId);
}
