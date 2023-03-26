package com.example.foodservice.controller;

import com.example.foodservice.entity.Product;
import com.example.foodservice.exception.ProductNotFoundedException;
import com.example.foodservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/food-service/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/default/create")
    public ResponseEntity<?> createDefaultProduct(@RequestBody Product product){
        productService.createProduct(product);
        return ResponseEntity.ok(responseMessage("Default product successfully created"));
    }

    @DeleteMapping("/default/delete")
    public ResponseEntity<?> deleteDefaultProduct(@RequestParam("productId") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(responseMessage("Default product successfully deleted"));
    }

    @PutMapping("/default/update")
    public ResponseEntity<?> updateDefaultProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseEntity.ok(responseMessage("Default product successfully updated"));
    }

    @GetMapping("/default/products")
    public ResponseEntity<?> getListDefaultProducts(){
        List<Product> products = productService.getDefaultProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/default/products/category")
    public ResponseEntity<?> getDefaultCategoryProductList(@RequestParam("code") String categoryCode){
        List<Product> products = productService.getProductsByCategory(categoryCode);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/custom/create")
    public ResponseEntity<?> createCustomProduct(@RequestBody Product product){
        productService.createProduct(product);
        return ResponseEntity.ok(responseMessage("Custom product successfully created"));
    }

    @DeleteMapping("/custom/delete")
    public ResponseEntity<?> deleteCustomProduct(@RequestParam("productId") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(responseMessage("Custom product successfully deleted"));
    }

    @PutMapping("/custom/update")
    public ResponseEntity<?> updateCustomProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseEntity.ok(responseMessage("Custom product successfully updated"));
    }

    @GetMapping("/custom/products")
    public ResponseEntity<?> getListCustomProducts(@RequestParam("userId") Long userId){
        List<Product> products = productService.getCustomProducts(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/custom/products/category")
    public ResponseEntity<?> getCustomCategoryProductList(@RequestParam("code") String categoryCode,
                                                          @RequestParam("userId") Long userId){
        List<Product> products = productService.getCustomProductsByCategory(categoryCode, userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/")
    public ResponseEntity<?> getProduct(@RequestParam("productId") Long productId) throws ProductNotFoundedException {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @ExceptionHandler(value = ProductNotFoundedException.class)
    public ResponseEntity<?> productNotFounded(){
        Product product = new Product();
        product.setName("Product with this id not found");
        return ResponseEntity.ok(product);
    }

    private Map<String, String> responseMessage(String message){
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
