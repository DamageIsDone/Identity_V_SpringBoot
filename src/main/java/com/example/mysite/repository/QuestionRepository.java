package com.example.mysite.repository;

import com.example.mysite.classes.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}