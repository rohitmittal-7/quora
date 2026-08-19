package com.example.quoraspringproject.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="answers")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable=false)
    private Question question;
}
