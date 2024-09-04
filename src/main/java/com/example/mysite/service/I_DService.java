package com.example.mysite.service;

import com.example.mysite.classes.I_D;
import com.example.mysite.repository.I_DRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class I_DService {
    @Autowired
    private I_DRepository i_dRepository;

    public List<I_D> getAllIDs() {
        return i_dRepository.findAll();
    }

    public I_D getIDById(I_D.I_DKey i_dKey) {
        return i_dRepository.findById(i_dKey).orElse(null);
    }

    public I_D saveID(I_D i_d) {
        return i_dRepository.save(i_d);
    }

    public void deleteID(I_D.I_DKey i_dKey) {
        i_dRepository.deleteById(i_dKey);
    }
}
