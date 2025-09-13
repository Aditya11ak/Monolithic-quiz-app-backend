package com.ChakreAditya.MonolithicApplication.controller;


import com.ChakreAditya.MonolithicApplication.model.QuestionWrapper;
import com.ChakreAditya.MonolithicApplication.model.Response;
import com.ChakreAditya.MonolithicApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
       return quizService.getQuizQuestionsById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateRightAnswers(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateAnswers(id,responses);
    }
}
