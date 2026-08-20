package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.QuestionRequest;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;



    @PostMapping("/api/questions")
    public Question createQuestion(@RequestBody QuestionRequest request){
        return questionService.createQuestion(
                request.getTitle(),
                request.getDescription(),
                request.getUserId());
    }


}
