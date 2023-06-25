package com.huz.quizapp.service;

import com.huz.quizapp.exception.ErrorMessage;
import com.huz.quizapp.model.Question;
import com.huz.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void addQuestion(Question question) {
        if (questionRepository.existsByTitle(question.getQuestionTitle())) {
            throw new RuntimeException(String.format(ErrorMessage.QUESTION_ALREADY_EXIST_MESSAGE, question.getQuestionTitle()));
        }

        questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        if (questionRepository.findAll().size() == 0) {
            throw new RuntimeException(String.format(ErrorMessage.NOT_FOUND_ANY_QUESTION_MESSAGE));
        }

        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {

        return questionRepository.findQuestionById(id).orElseThrow(() ->
                new RuntimeException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);

        if (!questionRepository.existsById(questionRepository.findQuestion(id))) {
            throw new RuntimeException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id));
        }

        questionRepository.delete(question);
    }
}
