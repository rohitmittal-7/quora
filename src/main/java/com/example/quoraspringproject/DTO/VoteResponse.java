package com.example.quoraspringproject.DTO;

import com.example.quoraspringproject.Enums.VoteType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VoteResponse {
    private Long id;
    private VoteType type;
    private Long userId;
    private Long answerId;
}
