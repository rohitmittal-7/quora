package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.VoteRequest;
import com.example.quoraspringproject.DTO.VoteResponse;
import com.example.quoraspringproject.Models.Vote;
import com.example.quoraspringproject.Service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{api}")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/answers/{answerId}/vote")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteResponse vote(@PathVariable Long answerId, @RequestBody VoteRequest request) {
        return voteService.vote(answerId, request);
    }

    @DeleteMapping("/answers/{answerId}/vote/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVote(
            @PathVariable Long answerId,
            @PathVariable Long userId) {

        voteService.deleteVote(answerId, userId);
    }
}