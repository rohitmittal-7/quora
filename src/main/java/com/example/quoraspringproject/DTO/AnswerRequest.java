package com.example.quoraspringproject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    private String content;
    private Long userId;
    private Long questionId;
}
