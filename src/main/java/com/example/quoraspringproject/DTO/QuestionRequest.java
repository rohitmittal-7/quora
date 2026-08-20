package com.example.quoraspringproject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {
    private String title;
    private String description;
    private Long userId;
}
