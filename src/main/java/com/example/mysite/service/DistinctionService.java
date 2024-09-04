package com.example.mysite.service;

import com.example.mysite.classes.Distinction;
import com.example.mysite.repository.DistinctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistinctionService {
    private DistinctionRepository distinctionRepository;

    @Autowired
    public DistinctionService(DistinctionRepository distinctionRepository) {
        this.distinctionRepository = distinctionRepository;
    }
}
