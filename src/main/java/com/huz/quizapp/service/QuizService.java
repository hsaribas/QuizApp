package com.huz.quizapp.service;

import com.huz.quizapp.model.Question;
import com.huz.quizapp.model.Quiz;
import com.huz.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionService questionService;

    public void createQuiz(String category, int questionNum, String title) {
        List<Question> randomQuestions = questionService.getRandomQuestionsByCategory(category, questionNum);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(randomQuestions);

        quizRepository.save(quiz);
    }
}
