package com.huz.quizapp.controller;

import com.huz.quizapp.dto.QuestionDTO;
import com.huz.quizapp.dto.Answer;
import com.huz.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int questionNum,
                                             @RequestParam String title) {
        quizService.createQuiz(category, questionNum, title);

        return new ResponseEntity<>("New Quiz Created", HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable Long id, @RequestBody List<Answer> answers) {
        int score = quizService.submitQuiz(id, answers);

        return new ResponseEntity<>("Your score is: " + score, HttpStatus.OK);
    }
}
