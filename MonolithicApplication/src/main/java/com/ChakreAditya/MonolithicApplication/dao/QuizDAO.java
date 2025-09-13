package com.ChakreAditya.MonolithicApplication.dao;

import com.ChakreAditya.MonolithicApplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
