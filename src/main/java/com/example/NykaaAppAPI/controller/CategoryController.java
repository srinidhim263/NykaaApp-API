package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
import com.example.NykaaAppAPI.exception.UnableToInsertException;
import com.example.NykaaAppAPI.exception.UnableToUpdateException;
import com.example.NykaaAppAPI.model.Category;
import com.example.NykaaAppAPI.model.Role;
import com.example.NykaaAppAPI.response.APIResponse;
import com.example.NykaaAppAPI.response.DeletedResponse;
import com.example.NykaaAppAPI.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private APIResponse apiResponse;
    @Secured({Role.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<APIResponse> addCategory(@RequestBody Category category) {
        Category addedCategory = categoryService.addCategory(category);
        if(addedCategory == null){
            throw new UnableToInsertException("Unable To Insert category");
        }
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addedCategory);
        return new ResponseEntity<>(apiResponse , HttpStatus.CREATED);
    }

    @Secured({Role.ROLE_ADMIN , Role.ROLE_USER})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewCategory(@PathVariable int categoryId) {
        List<Category> categories = categoryService.viewCategory();
        if(categories==null){
            throw new ResourceNotFoundException("Unable to view category");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categories);
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }
    @Secured({Role.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<APIResponse> updateCategory(@RequestBody Category category) {
        Category updatedCategory  = categoryService.updateCategory(category);
        if(updatedCategory == null){
            throw new UnableToUpdateException("Unable to update category");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updatedCategory);
        return  new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @Secured({Role.ROLE_ADMIN})
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new DeletedResponse());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
