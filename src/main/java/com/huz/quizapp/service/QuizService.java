package com.huz.quizapp.service;

import com.huz.quizapp.dto.QuestionDTO;
import com.huz.quizapp.model.Question;
import com.huz.quizapp.model.Quiz;
import com.huz.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<QuestionDTO> getQuiz(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionDTO> DTOQuestions = new ArrayList<>();

        for(Question q : questions){
            QuestionDTO questionDTO = new QuestionDTO(q.getId(), q.getQuestionTitle(), q.getOption1(),
                                                      q.getOption2(), q.getOption3(), q.getOption4());
            DTOQuestions.add(questionDTO);
        }

        return DTOQuestions;
    }
}
