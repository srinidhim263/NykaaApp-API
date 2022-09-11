package com.example.NykaaAppAPI.service;

import com.example.NykaaAppAPI.exception.ResourceAlreadyExistException;
import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
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

    public Product addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(" category does not exist."));

        product.setCategory(category);

        boolean isProductExists = productRepository.findByProductName(product.getProductName()).isPresent();
        if (isProductExists)
            throw new ResourceAlreadyExistException("Product already exists.");
        return productRepository.save(product);
    }

    public List<Product> viewProducts() {

        return productRepository.findAll();
    }

    public Product updateProduct(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        if (productRequest.getProductId() == null)
            throw new ResourceNotFoundException("No product with id "
                    + productRequest.getProductId());
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("No category with category id "
                        + productRequest.getCategoryId()));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("No product with id "
                        + productId));
        productRepository.deleteById(productId);
    }

    public Product findById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id" +
                        productId));
    }
}
