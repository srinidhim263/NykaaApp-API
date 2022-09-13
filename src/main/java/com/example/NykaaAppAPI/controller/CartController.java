package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.model.CartProduct;
import com.example.NykaaAppAPI.model.Role;
import com.example.NykaaAppAPI.request.CartRequest;
import com.example.NykaaAppAPI.response.APIResponse;
import com.example.NykaaAppAPI.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value={"http://localhost:3000"})
@RestController
@RequestMapping
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private APIResponse apiResponse;
    @Secured({Role.ROLE_USER})
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addToCart(@RequestBody CartRequest cartRequest) {
        List<CartProduct> cartProducts = cartService.addToCart(cartRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Secured({Role.ROLE_USER, Role.ROLE_ADMIN})
    @GetMapping("/user/{userId}")
    public ResponseEntity<APIResponse> showCartOfUserById(@PathVariable Integer userId) {
        List<CartProduct> cartProducts = cartService.showCartOfUserById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Secured({Role.ROLE_USER})
    @DeleteMapping("/{cartProductId}")
    public ResponseEntity<APIResponse> removeProductFromCart(@PathVariable Integer cartProductId) {
        List<CartProduct> cartProducts = cartService.removeProductFromCart(cartProductId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
