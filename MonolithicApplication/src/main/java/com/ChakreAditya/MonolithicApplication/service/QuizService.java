package com.ChakreAditya.MonolithicApplication.service;

import com.ChakreAditya.MonolithicApplication.dao.QuestionDAO;
import com.ChakreAditya.MonolithicApplication.dao.QuizDAO;
import com.ChakreAditya.MonolithicApplication.model.Question;
import com.ChakreAditya.MonolithicApplication.model.QuestionWrapper;
import com.ChakreAditya.MonolithicApplication.model.Quiz;
import com.ChakreAditya.MonolithicApplication.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question>  questions = questionDAO.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(int id) {

        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questionsFromDB=null;

        if(quiz.isPresent()) {
            questionsFromDB = quiz.get().getQuestions();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<QuestionWrapper> questions = new ArrayList<>();
        for(Question q: questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questions.add(qw);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateAnswers(int id, List<Response> responses) {
        List<Question> questions = null;
        if(quizDAO.findById(id).isPresent()){
            questions = quizDAO.findById(id).get().getQuestions();
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int rightAnswersCount = 0;
        int i = 0;

        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                rightAnswersCount++;
            i++;
        }
        return new ResponseEntity<>(rightAnswersCount,HttpStatus.OK);
    }

}
