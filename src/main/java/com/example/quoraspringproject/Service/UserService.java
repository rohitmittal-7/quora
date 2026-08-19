package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User createUser(User user){
        return userRepository.save(user);
    }
}
