package com.ecommerce.service;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id){

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"));
    }
    @Override
    public Category updateCategory(
            Long id,
            Category category){

        Category existingCategory =
                getCategoryById(id);

        existingCategory.setName(
                category.getName());

        return categoryRepository
                .save(existingCategory);
    }
    @Override
    public void deleteCategory(Long id){
        Category category =
                getCategoryById(id);

        categoryRepository.delete(category);
    }
}
