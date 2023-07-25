package com.huz.quizapp.service;

import com.huz.quizapp.dto.Answer;
import com.huz.quizapp.dto.QuestionDTO;
import com.huz.quizapp.exception.ErrorMessage;
import com.huz.quizapp.model.Question;
import com.huz.quizapp.model.Quiz;
import com.huz.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<QuestionDTO> getQuizQuestions(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() ->
                new RuntimeException(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE));
        List<Question> questions = quiz.getQuestions();
        List<QuestionDTO> DTOQuestions = new ArrayList<>();

        for (Question q : questions) {
            QuestionDTO questionDTO = new QuestionDTO(q.getId(), q.getQuestionTitle(), q.getOption1(),
                    q.getOption2(), q.getOption3(), q.getOption4());
            DTOQuestions.add(questionDTO);
        }

        return DTOQuestions;
    }

    public int submitQuiz(Long id, List<Answer> answers) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() ->
                new RuntimeException(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE));
        List<Question> questions = quiz.getQuestions();
        int score = 0;
        int i = 0;

        for (Answer a : answers) {
            if (a.getResponse().equals(questions.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }

        return score;
    }
}
