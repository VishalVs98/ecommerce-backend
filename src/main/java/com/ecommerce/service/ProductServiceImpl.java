package com.ecommerce.service;

import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
      public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        Category category =
                categoryRepository
                        .findById(dto.getCategoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(category);
        Product saved =
                productRepository.save(product);
        return new ProductResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStock(),
                saved.getCategory().getName()
        );
    }
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products =
                productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory().getName()

                )).toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getName()
        );
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO product) {
        Product existingProduct =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"));

        Category category =
                categoryRepository
                        .findById(product.getCategoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(
                product.getDescription());
        existingProduct.setPrice(
                product.getPrice());
        existingProduct.setStock(
                product.getStock());
        existingProduct.setCategory(category);

        Product updatedProduct =
                productRepository.save(existingProduct);

        return new ProductResponseDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getStock(),
                updatedProduct.getCategory().getName()
        );
    }
    @Override
    public void deleteProduct(Long id) {
        Product existingProduct =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"));
        productRepository.delete(existingProduct);
    }
}
