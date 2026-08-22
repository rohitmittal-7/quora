package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.DTO.AnswerResponse;
import com.example.quoraspringproject.DTO.AnswerUpdateRequest;
import com.example.quoraspringproject.Exception.ResourceNotFoundException;
import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.AnswerRepository;
import com.example.quoraspringproject.Repository.QuestionRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

public Answer createAnswer(String content , Long userId, Long questionId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+userId));
        Question question= questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question not found with id: "+questionId));

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setUser(user);
        answer.setQuestion(question);
        return answerRepository.save(answer);
}

    public List<AnswerResponse> getAnswersByQuestionId(Long questionId){
        List<Answer> answers= answerRepository.findByQuestionId(questionId);
        return answers.stream().map(answer-> new AnswerResponse(
                answer.getId(),
                answer.getContent(),
                answer.getUser().getUsername()
        )).toList();
    }

    public void deleteAnswer(Long id) {

        Answer answer = answerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Answer not found with id: " + id
                        )
                );

        answerRepository.delete(answer);
    }

    //Update answer method
    public AnswerResponse updateAnswer(Long id, AnswerUpdateRequest request){
        Answer answer = answerRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Answer not found with id: "+id));
        answer.setContent(request.getContent());
        Answer updateAnswer = answerRepository.save(answer);
        return new AnswerResponse(
                updateAnswer.getId(),
                updateAnswer.getContent(),
                updateAnswer.getUser().getUsername());
    }
}
