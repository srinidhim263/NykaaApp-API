package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.model.Product;
import com.example.NykaaAppAPI.model.Role;
import com.example.NykaaAppAPI.request.ProductRequest;
import com.example.NykaaAppAPI.response.APIResponse;
import com.example.NykaaAppAPI.response.SuccessResponse;
import com.example.NykaaAppAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private APIResponse apiResponse;
   // @Secured({Role.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<APIResponse> addProduct(@RequestBody ProductRequest productRequest) {
        Product addedProduct = productService.addProduct(productRequest);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addedProduct);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

   // @Secured({Role.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updatedProduct);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

   // @Secured({Role.ROLE_ADMIN})
    @DeleteMapping("/{productId}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new SuccessResponse());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

   // @Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllProducts() {
        List<Product> products = productService.viewProducts();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(products);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

   // @Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
    @GetMapping("/{productId}")
    public ResponseEntity<APIResponse> viewProductByProductId(@PathVariable Integer productId) {
        Product product = productService.findById(productId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(product);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


}


