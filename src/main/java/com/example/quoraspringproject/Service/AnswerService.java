package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.DTO.AnswerResponse;
import com.example.quoraspringproject.DTO.AnswerUpdateRequest;
import com.example.quoraspringproject.Enums.VoteType;
import com.example.quoraspringproject.Exception.ResourceNotFoundException;
import com.example.quoraspringproject.Models.Answer;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.AnswerRepository;
import com.example.quoraspringproject.Repository.QuestionRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import com.example.quoraspringproject.Repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
public Answer createAnswer(String content , Long userId, Long questionId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+userId));
        Question question= questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question not found with id: "+questionId));

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setUser(user);
        answer.setQuestion(question);
        return answerRepository.save(answer);
}

    public List<AnswerResponse> getAnswersByQuestionId(Long questionId){
        List<Answer> answers= answerRepository.findByQuestionId(questionId);
        return answers.stream().map(answer-> {
                    long upvotes = voteRepository.countByAnswerIdAndType(
                                    answer.getId(),
                                    VoteType.UPVOTE
                            );

                    long downvotes = voteRepository.countByAnswerIdAndType(
                                    answer.getId(),
                                    VoteType.DOWNVOTE
                            );

                    return new AnswerResponse(
                            answer.getId(),
                            answer.getContent(),
                            answer.getUser().getUsername(),
                            upvotes,
                            downvotes);}).toList();
}

    public void deleteAnswer(Long id) {

        Answer answer = answerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Answer not found with id: " + id
                        )
                );

        answerRepository.delete(answer);
    }

    //Update answer method
    public AnswerResponse updateAnswer(Long id, AnswerUpdateRequest request){
        Answer answer = answerRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Answer not found with id: "+id));
        answer.setContent(request.getContent());
        Answer updateAnswer = answerRepository.save(answer);

        long upvotes = voteRepository.countByAnswerIdAndType(
                updateAnswer.getId(),
                VoteType.UPVOTE
        );

        long downvotes = voteRepository.countByAnswerIdAndType(
                updateAnswer.getId(),
                VoteType.DOWNVOTE
        );
        return new AnswerResponse(
                updateAnswer.getId(),
                updateAnswer.getContent(),
                updateAnswer.getUser().getUsername(),upvotes,downvotes
        );
    }
}
