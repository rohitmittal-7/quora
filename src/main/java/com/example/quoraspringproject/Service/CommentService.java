package com.example.quoraspringproject.Service;
import com.example.quoraspringproject.DTO.CommentRequest;
import com.example.quoraspringproject.DTO.CommentResponse;
import com.example.quoraspringproject.DTO.CommentUpdateRequest;
import com.example.quoraspringproject.Exception.ResourceNotFoundException;
import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Models.Comment;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.AnswerRepository;
import com.example.quoraspringproject.Repository.CommentRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public CommentResponse createComment(
            Long answerId,
            CommentRequest request) {

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

        Comment comment = new Comment();

        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setAnswer(answer);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getUser().getUsername()
        );
    }

    public List<CommentResponse> getCommentsByAnswerId(Long answerId) {

        answerRepository.findById(answerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Answer not found with id: " + answerId
                        )
                );

        List<Comment> comments = commentRepository.findByAnswerId(answerId);

        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getUsername()
                ))
                .toList();
    }

    //Update comment method

    public CommentResponse updateComment(
            Long id,
            CommentUpdateRequest request) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Comment not found with id: " + id
                        )
                );

        comment.setContent(request.getContent());

        Comment updatedComment = commentRepository.save(comment);

        return new CommentResponse(
                updatedComment.getId(),
                updatedComment.getContent(),
                updatedComment.getUser().getUsername()
        );
    }

    //Delete comment method

    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Comment not found with id: " + id
                        )
                );

        commentRepository.delete(comment);
    }
}