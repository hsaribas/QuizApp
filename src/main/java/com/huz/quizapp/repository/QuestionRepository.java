package com.huz.quizapp.repository;

import com.huz.quizapp.model.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Boolean existsByTitle(String title);

    @EntityGraph(attributePaths = "id")
    Optional<Question> findQuestionById(Long id);

    @EntityGraph(attributePaths = "id")
    Integer findQuestion(Long id);
}
