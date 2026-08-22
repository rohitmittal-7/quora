package com.example.quoraspringproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class AnswerResponse {
    private Long id;
    private String content;
    private String username;
}
