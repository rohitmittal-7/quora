package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.DTO.VoteRequest;
import com.example.quoraspringproject.DTO.VoteResponse;
import com.example.quoraspringproject.Exception.ResourceNotFoundException;
import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Models.Vote;
import com.example.quoraspringproject.Repository.AnswerRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import com.example.quoraspringproject.Repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public VoteResponse vote(Long answerId, VoteRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + request.getUserId()
                        )
                );

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Answer not found with id: " + answerId
                        )
                );

        Vote vote = voteRepository
                .findByUserIdAndAnswerId(
                        request.getUserId(),
                        answerId
                )
                .orElseGet(Vote::new);

        vote.setUser(user);
        vote.setAnswer(answer);
        vote.setType(request.getType());
        Vote savedVote = voteRepository.save(vote);
        return new VoteResponse(
                savedVote.getId(),
                savedVote.getType(),
                savedVote.getUser().getId(),
                savedVote.getAnswer().getId());
}
    public void deleteVote(Long answerId, Long userId) {

        Vote vote = voteRepository
                .findByUserIdAndAnswerId(userId, answerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vote not found for this user on this answer"
                        )
                );

        voteRepository.delete(vote);
    }
}
