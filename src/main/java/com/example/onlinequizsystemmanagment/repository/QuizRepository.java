package com.example.onlinequizsystemmanagment.repository;

import com.example.onlinequizsystemmanagment.model.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

    List<Quiz> findByTag(String tag);

}
