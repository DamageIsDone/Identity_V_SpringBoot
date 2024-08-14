package com.example.mysite.controller;

import com.example.mysite.classes.Survey;
import com.example.mysite.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/{title}")
    public Survey getSurveyByTitle(@PathVariable String title){
        return surveyService.findByTitle(title);
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey){
        return surveyService.saveSurvey(survey);
    }
}
