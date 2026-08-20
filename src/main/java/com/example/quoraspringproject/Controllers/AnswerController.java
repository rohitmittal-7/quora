package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.AnswerRequest;
import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/api/answers")
    public Answer createAnswer(@RequestBody AnswerRequest request){
        return answerService.createAnswer(request.getContent(),
                request.getUserId(),
                request.getQuestionId());
    }

}
