package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product prod=productService.addProduct(product);
        return new ResponseEntity<>(prod,HttpStatus.CREATED);
    }
    @GetMapping
    public List<Product> getProduct(){
        return productService.getAllProducts();
    }
}
