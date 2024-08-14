package com.example.mysite.service;

import com.example.mysite.classes.Survey;
import com.example.mysite.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    public Survey findByTitle(String title){
        return surveyRepository.findByTitle(title);
    }

    public Survey saveSurvey(Survey survey){
        return surveyRepository.save(survey);
    }
}
