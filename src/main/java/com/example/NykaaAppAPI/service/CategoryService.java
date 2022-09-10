package com.example.NykaaAppAPI.service;

import com.example.NykaaAppAPI.exception.ResourceAlreadyExistException;
import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
import com.example.NykaaAppAPI.model.Category;
import com.example.NykaaAppAPI.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        boolean isExists = categoryRepository.findByCategoryName(category.getCategoryName()).isPresent();
        if (isExists)
            throw new ResourceAlreadyExistException("Category already exists.");

         return  categoryRepository.save(category);

    }

    public List<Category> viewCategory() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category) {
        if(category.getCategoryId() != null && category.getCategoryId() > 0 ){
            categoryRepository.findById(category.getCategoryId())
                    .orElseThrow(() ->new ResourceAlreadyExistException("No Category " +
                            "With Id" + category.getCategoryId()));
           return  categoryRepository.save(category);

        } else {
            throw new ResourceNotFoundException("Invalid Category Id");
        }

    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(()->
       new ResourceNotFoundException("No Category with Id" + categoryId) );
        categoryRepository.deleteById(categoryId);
    }

}
