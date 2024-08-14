package com.example.mysite.repository;

import com.example.mysite.classes.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {
    Survey findByTitle(String title);
}
