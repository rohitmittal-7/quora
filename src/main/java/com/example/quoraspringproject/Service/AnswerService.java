package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.AnswerRepository;
import com.example.quoraspringproject.Repository.QuestionRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
private final AnswerRepository answerRepository;
private final QuestionRepository questionRepository;
private final UserRepository userRepository;

public Answer createAnswer(String content , Long userId, Long questionId){
User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
Question question= questionRepository.findById(questionId).orElseThrow(()->new RuntimeException());

Answer answer = new Answer();
answer.setContent(content);
answer.setUser(user);
answer.setQuestion(question);
return answerRepository.save(answer);

}

}
