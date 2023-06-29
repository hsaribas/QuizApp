package com.huz.quizapp.repository;

import com.huz.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Boolean existsByTitle(String title);

    Optional<Question> findQuestionById(Long id);

    List<Question> findByCategory(String category);

    Integer findQuestion(Long id);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category " +
            "ORDER BY RANDOM() LIMIT :questionNum", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int questionNum);
}
