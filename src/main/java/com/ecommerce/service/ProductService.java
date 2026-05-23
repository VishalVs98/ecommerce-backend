package com.ecommerce.service;

import com.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> getAllProducts();
}
