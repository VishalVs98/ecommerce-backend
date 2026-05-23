package com.ecommerce.service;

import com.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);
    List<Category> getAllCategories();
}
