package com.example.mysite.service;

import com.example.mysite.classes.Handheld;
import com.example.mysite.classes.Skill;
import com.example.mysite.repository.HandheldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandheldService {
    @Autowired
    private HandheldRepository handheldRepository;

    public Handheld getHandheldById(Integer id) {
        return handheldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Handheld not found with id: " + id));
    }
}
