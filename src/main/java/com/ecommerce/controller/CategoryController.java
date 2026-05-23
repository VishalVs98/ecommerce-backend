package com.ecommerce.controller;

import com.ecommerce.entity.Category;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category cate=categoryService.addCategory(category);
        return new ResponseEntity<>(cate, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Category> getCategory(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Category
    getCategoryById(
            @PathVariable Long id){

        return categoryService
                .getCategoryById(id);
    }


    @PutMapping("/{id}")
    public Category
    updateCategory(
            @PathVariable Long id,
            @RequestBody Category category){

        return categoryService
                .updateCategory(id,
                        category);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteCategory(
            @PathVariable Long id){

        categoryService.deleteCategory(id);

        return ResponseEntity.ok(
                "Category deleted successfully");
    }
}
