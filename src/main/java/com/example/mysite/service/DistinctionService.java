package com.example.mysite.service;

import com.example.mysite.classes.Distinction;
import com.example.mysite.repository.DistinctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistinctionService {
    @Autowired
    private DistinctionRepository distinctionRepository;

    public Optional<Distinction> findById(Integer id) {
        return distinctionRepository.findById(id);
    }

    public Distinction getDistinctionById(Integer id) {
        return distinctionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distinction not found with id: " + id));
    }
}
