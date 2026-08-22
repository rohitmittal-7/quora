package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.QuestionResponse;
import com.example.quoraspringproject.DTO.UserResponse;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Service.UserService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Builder
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService= userService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/questions")
    public List<QuestionResponse> getUserQuestions(
            @PathVariable Long id) {

        return userService.getUserQuestions(id);
    }
}
