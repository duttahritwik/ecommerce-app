package com.ecommerce.application.project.service;

import com.ecommerce.application.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category categoryToBeRemoved = categories.stream()
                .filter(category -> category.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Category with categoryId " + categoryId + " not found"));

        categories.remove(categoryToBeRemoved);
        return "Category with categoryID " + categoryId + " removed successfully.";
    }

    @Override
    public void updateCategory(Category category) {
        Category categoryToBeUpdated = categories.stream()
                .filter(category1 -> category1.getCategoryName().equals(category.getCategoryName()) ||
                        Objects.equals(category1.getCategoryId(), category.getCategoryId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with categoryId " + category.getCategoryId() + " or name " + category.getCategoryName() + " not found"));

        categories.remove(categoryToBeUpdated);
        categories.add(category);

    }
}
