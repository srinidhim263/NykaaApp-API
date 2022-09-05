package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.response.APIResponse;
import com.example.NykaaAppAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody NykaaUser nykaaUser) {
        APIResponse apiResponse = new APIResponse();
        NykaaUser registeredUser = userService.registerAsCustomer(nykaaUser);
        if (registeredUser == null) {
            throw new ResourceNotFoundException("Unable to register User");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(registeredUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody NykaaUser nykaaUser) {
        APIResponse apiResponse = new APIResponse();
        NykaaUser loggedInUser = userService.loginAsCustomer(nykaaUser);
        if (loggedInUser == null) {
            throw new ResourceNotFoundException("User Not found");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loggedInUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NykaaUser>> getAllUsers() {
        List<NykaaUser> bookUsers = userService.getAllUsers();
        if (bookUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookUsers, HttpStatus.OK);
    }



}
