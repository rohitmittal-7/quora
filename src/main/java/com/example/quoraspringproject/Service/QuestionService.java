package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.QuestionRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository){
        this.questionRepository=questionRepository;
        this.userRepository=userRepository;
    }

    public Question createQuestion(String title, String description,Long userId){
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setUser(user);
        return questionRepository.save(question);
    }

}
