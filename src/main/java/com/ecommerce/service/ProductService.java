package com.ecommerce.service;

import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO product);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id,
                          ProductRequestDTO product);
    void deleteProduct(Long id);
}
