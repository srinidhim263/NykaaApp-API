package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.model.Category;
import com.example.NykaaAppAPI.response.APIResponse;
import com.example.NykaaAppAPI.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<APIResponse> addCategory(@RequestBody Category category) {
        APIResponse apiResponse = new APIResponse();
        Category cat = categoryService.addCategory(category);
        if (cat == null) {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(cat);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
