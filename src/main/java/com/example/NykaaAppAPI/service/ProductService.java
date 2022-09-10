package com.example.NykaaAppAPI.service;

import com.example.NykaaAppAPI.model.Category;
import com.example.NykaaAppAPI.model.Product;
import com.example.NykaaAppAPI.repository.CategoryRepository;
import com.example.NykaaAppAPI.repository.ProductRepository;
import com.example.NykaaAppAPI.request.ProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
//    public Product addProduct(ProductRequest productRequest) {
//        Product product = new Product();
//        BeanUtils.copyProperties(productRequest , product);
//        Category category = categoryRepository.findById(productRequest.getCategoryId()
//        ).orElseThrow(()
//    }

    public List<Product> viewProduct() {
        return productRepository.findAll();
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int categoryId) {
        productRepository.deleteById(categoryId);
    }
}
