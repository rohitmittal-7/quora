package com.example.quoraspringproject.DTO;

import com.example.quoraspringproject.Enums.VoteType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {

    private Long userId;
    private VoteType type;
}
