package com.ecommerce.application.project.service;

import com.ecommerce.application.project.model.Category;
import com.ecommerce.application.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category categoryToBeRemoved = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Category with categoryID " + categoryId + " not found"));

        categoryRepository.delete(categoryToBeRemoved);
        return "Category with categoryID " + categoryId + " removed successfully.";
    }

    @Override
    public void updateCategory(Category category) {
        Category categoryInDatabase = categoryRepository.findById(category.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with category name " + category.getCategoryName() + " not found"));

        categoryInDatabase.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryInDatabase);
    }
}
