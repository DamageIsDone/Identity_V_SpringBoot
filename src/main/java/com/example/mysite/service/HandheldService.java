package com.example.mysite.service;

import com.example.mysite.classes.Handheld;
import com.example.mysite.repository.HandheldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandheldService {
    private HandheldRepository handheldRepository;

    @Autowired
    public HandheldService(HandheldRepository handheldRepository) {
        this.handheldRepository = handheldRepository;
    }
}
