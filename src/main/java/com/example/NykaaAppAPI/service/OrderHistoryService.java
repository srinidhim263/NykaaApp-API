package com.example.NykaaAppAPI.service;
import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
import com.example.NykaaAppAPI.model.Category;
import com.example.NykaaAppAPI.model.NykaaUser;

import com.example.NykaaAppAPI.model.OrderHistory;
import com.example.NykaaAppAPI.model.Product;
import com.example.NykaaAppAPI.repository.CategoryRepository;
import com.example.NykaaAppAPI.repository.OrderHistoryRepository;
import com.example.NykaaAppAPI.repository.ProductRepository;


import com.example.NykaaAppAPI.repository.UserRepository;
import com.example.NykaaAppAPI.request.HistoryRequest;
import com.example.NykaaAppAPI.response.OrderHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

}
