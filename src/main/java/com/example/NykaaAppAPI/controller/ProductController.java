package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.model.Product;
import com.example.NykaaAppAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

//    @PostMapping
//    public void addProduct(@RequestBody Product product) {
//
//        productService.addProduct(product);
//    }

    @GetMapping("/all")
    public List<Product> viewProduct(@PathVariable int id) {
        return productService.viewProduct();
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {

        productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable int id) {

        productService.deleteProduct(id);
    }
}


