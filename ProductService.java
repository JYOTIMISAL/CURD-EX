package com.its.CURD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.its.CURD.entity.Category;
import com.its.CURD.entity.Product;
import com.its.CURD.repository.CategoryRepository;
import com.its.CURD.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all products with pagination
    public org.hibernate.query.Page getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);  // ✅ Fixed return type
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Create new product under a category
    public Product createProduct(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);  // ✅ Fixed: Removed unnecessary casting
    }

    // Update product details
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Delete product by ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);  // ✅ Fixed: Removed unnecessary casting
    }
}
