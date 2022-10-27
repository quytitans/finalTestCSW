package com.example.csw_api.service;

import com.example.csw_api.entity.Product;
import com.example.csw_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean updateQuantity(int id, int amount) {
        Product product = findById(id);
        product.setQuantity(product.getQuantity() + amount);
        productRepository.save(product);
        return true;
    }
}
