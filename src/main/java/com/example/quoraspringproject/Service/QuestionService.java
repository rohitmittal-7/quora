package com.example.quoraspringproject.Service;

import com.example.quoraspringproject.DTO.QuestionResponse;
import com.example.quoraspringproject.DTO.QuestionUpdateRequest;
import com.example.quoraspringproject.Exception.ResourceNotFoundException;
import com.example.quoraspringproject.Models.Question;
import com.example.quoraspringproject.Models.User;
import com.example.quoraspringproject.Repository.AnswerRepository;
import com.example.quoraspringproject.Repository.QuestionRepository;
import com.example.quoraspringproject.Repository.UserRepository;
import lombok.Builder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository){
        this.questionRepository=questionRepository;
        this.userRepository=userRepository;
    }

//Create a new question method
    public Question createQuestion(String title, String description,Long userId){
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setUser(user);
        return questionRepository.save(question);
    }

//Get List of question method
        public Page<QuestionResponse> getAllQuestions(
                int page,
                int size){
            Pageable pageable = PageRequest.of(page, size);

            Page<Question> questions= questionRepository.findAll(pageable);
            return questions.map(question -> new QuestionResponse(
                    question.getId(),
                    question.getTitle(),
                    question.getDescription(),
                    question.getUser().getUsername()
            ));
}

//Find question by id method
        public QuestionResponse getQuestionById(Long id){
        Question question = questionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question not found with id: " +id));
        return new QuestionResponse(
                question.getId(),
                question.getTitle(),
                question.getDescription(),
                question.getUser().getUsername());
    }

//Delete question method
        public void deleteQuestion(Long id){
            Question question = questionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
                                questionRepository.delete(question);
        }

//Update Question method
    public QuestionResponse updateQuestion(
            Long id,
            QuestionUpdateRequest request) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + id
                        )
                );

        question.setTitle(request.getTitle());
        question.setDescription(request.getDescription());

        Question updatedQuestion = questionRepository.save(question);

        return new QuestionResponse(
                updatedQuestion.getId(),
                updatedQuestion.getTitle(),
                updatedQuestion.getDescription(),
                updatedQuestion.getUser().getUsername()
        );
    }
}
