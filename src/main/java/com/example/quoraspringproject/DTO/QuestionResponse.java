package com.example.quoraspringproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String title;
    private String description;
    private String username;
}
