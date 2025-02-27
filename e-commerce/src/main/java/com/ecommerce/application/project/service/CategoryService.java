package com.ecommerce.application.project.service;

import com.ecommerce.application.project.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    void updateCategory(Category category);
}
