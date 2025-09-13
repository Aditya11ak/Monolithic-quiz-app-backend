package com.ChakreAditya.MonolithicApplication.service;

import com.ChakreAditya.MonolithicApplication.dao.QuestionDAO;
import com.ChakreAditya.MonolithicApplication.model.Question;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK) ;
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDAO.save(question);
            return new ResponseEntity<>("Successfully added the question", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add Question", HttpStatus.BAD_GATEWAY);
        }
}

    public ResponseEntity<String> UpdateQuestion(Question question) {
        try{
            if(question.getId() != null) {
                questionDAO.save(question);
            }else{
                throw new EntityNotFoundException("Question ID not found.");
            }
            return new ResponseEntity<>("Successfully Updated the data",HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }  catch (Exception e) {
            return new ResponseEntity<>("Failed to Updated the data",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(int id) {
       try{
           if(questionDAO.existsById(id)) {
               questionDAO.deleteById(id);
               return new ResponseEntity<>("Entry Deleted successfully", HttpStatus.OK);
           }else{
               throw new EntityNotFoundException("Entry with given id not found.");
           }

       }catch (EntityNotFoundException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }  catch (Exception e) {
           return new ResponseEntity<>("Failed to  Delete the entry",HttpStatus.BAD_REQUEST);
       }
    }
}
