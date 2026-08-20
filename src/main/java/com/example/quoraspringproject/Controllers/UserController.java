package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Service.UserService;
import lombok.Builder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder

public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService= userService;
    }
    @PostMapping("/api/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
