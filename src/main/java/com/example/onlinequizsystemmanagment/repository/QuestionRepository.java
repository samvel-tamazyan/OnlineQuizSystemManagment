package com.example.onlinequizsystemmanagment.repository;

import com.example.onlinequizsystemmanagment.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByQuizId(Long quizId);
}