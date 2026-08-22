package com.example.quoraspringproject.Repository;

import com.example.quoraspringproject.Enums.VoteType;
import com.example.quoraspringproject.Models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findByUserIdAndAnswerId(Long userId, Long answerId);
    long countByAnswerIdAndType(Long answerId, VoteType type);


}
