package com.huz.quizapp.controller;

import com.huz.quizapp.model.Question;
import com.huz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);

        return new ResponseEntity<>("Question Successfully Added", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {

        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {

        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {

        return ResponseEntity.ok(questionService.getQuestionByCategory(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);

        return new ResponseEntity<>("Question Successfully Deleted", HttpStatus.OK);
    }
}
