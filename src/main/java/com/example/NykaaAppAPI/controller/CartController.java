package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
}
