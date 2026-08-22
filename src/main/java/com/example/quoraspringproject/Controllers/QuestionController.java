package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.QuestionRequest;
import com.example.quoraspringproject.DTO.QuestionResponse;
import com.example.quoraspringproject.DTO.QuestionUpdateRequest;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/questions")
@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(@RequestBody QuestionRequest request){
        return questionService.createQuestion(
                request.getTitle(),
                request.getDescription(),
                request.getUserId());
    }

    @GetMapping
    public List<QuestionResponse> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(@PathVariable Long id){
      return questionService.getQuestionById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @PatchMapping("/{id}")
    public QuestionResponse updateQuestion(@PathVariable Long id, @RequestBody QuestionUpdateRequest request){
        return questionService.updateQuestion(id,request);
    }

}
