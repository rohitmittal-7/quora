package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user){
        return UserService.createUser(user);
    }
}
