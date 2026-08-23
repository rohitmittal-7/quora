package com.example.quoraspringproject.Controllers;

import com.example.quoraspringproject.DTO.CommentRequest;
import com.example.quoraspringproject.DTO.CommentResponse;
import com.example.quoraspringproject.DTO.CommentUpdateRequest;
import com.example.quoraspringproject.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/answers/{answerId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(
            @PathVariable Long answerId,
            @RequestBody CommentRequest request) {

        return commentService.createComment(answerId, request);
    }

    @GetMapping("/answers/{answerId}/comments")
    public List<CommentResponse> getComments(
            @PathVariable Long answerId) {

        return commentService.getCommentsByAnswerId(answerId);
    }

    @PatchMapping("/comments/{id}")
    public CommentResponse updateComment(
            @PathVariable Long id,
            @RequestBody CommentUpdateRequest request) {
            return commentService.updateComment(id,request);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);
    }
}