package com.example.NykaaAppAPI.controller;

import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public void addUser(@RequestBody NykaaUser nykaaUser){

        userService.addUser(nykaaUser);
    }
    @GetMapping("/all")
    public List<NykaaUser> viewUser(@PathVariable int id){
        return userService.viewUser();

    }
    @PutMapping
    public void updateUser(@RequestBody NykaaUser nykaaUser){

        userService.updateUser(nykaaUser);
    }
    @DeleteMapping
    public void deleteUser(@PathVariable int id){

        userService.deleteUser(id);
    }
}
