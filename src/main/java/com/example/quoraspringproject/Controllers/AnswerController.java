package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.AnswerRequest;
import com.example.quoraspringproject.DTO.AnswerResponse;
import com.example.quoraspringproject.DTO.AnswerUpdateRequest;
import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/api/answers")
    @ResponseStatus(HttpStatus.CREATED)
    public Answer createAnswer(@RequestBody AnswerRequest request){
        return answerService.createAnswer(
                request.getContent(),
                request.getUserId(),
                request.getQuestionId());
    }


    @GetMapping("/api/questions/{questionId}/answers")
    public List<AnswerResponse> getAnswersByQuestionId(@PathVariable Long questionId){
        return answerService.getAnswersByQuestionId(questionId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(@PathVariable Long id){
        answerService.deleteAnswer(id);
    }

    @PatchMapping("api/answers/{id}")
    public AnswerResponse updateAnswer(@PathVariable Long id, @RequestBody AnswerUpdateRequest request){
        return answerService.updateAnswer(id,request);
    }
}
