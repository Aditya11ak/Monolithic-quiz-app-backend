package com.ChakreAditya.MonolithicApplication.controller;

import com.ChakreAditya.MonolithicApplication.model.Question;
import com.ChakreAditya.MonolithicApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    //getting all questions
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    //finding questions by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }

    // adding question
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    //updating question
    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.UpdateQuestion(question);
    }

    //deleting question
    @DeleteMapping("deleteQuestion/{Id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int Id){
        return questionService.deleteQuestion(Id);
    }
}
