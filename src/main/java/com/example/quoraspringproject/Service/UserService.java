package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.DTO.QuestionResponse;
import com.example.quoraspringproject.DTO.UserResponse;
import com.example.quoraspringproject.Exception.ResourceNotFoundException;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.QuestionRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public UserService(UserRepository userRepository, QuestionRepository questionRepository){
        this.userRepository=userRepository;
        this.questionRepository = questionRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio());
    }

    public List<QuestionResponse> getUserQuestions(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        )
                );

        List<Question> questions = questionRepository.findByUserId(userId);

        return questions.stream()
                .map(question -> new QuestionResponse(
                        question.getId(),
                        question.getTitle(),
                        question.getDescription(),
                        question.getUser().getUsername()
                ))
                .toList();
    }
}
