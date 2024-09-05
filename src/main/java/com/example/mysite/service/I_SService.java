package com.example.mysite.service;

import com.example.mysite.classes.I_S;
import com.example.mysite.repository.I_DRepository;
import com.example.mysite.repository.I_SRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class I_SService {
    @Autowired
    private I_SRepository i_sRepository;

    public  List<I_S> getALLISs() {
        return i_sRepository.findAll();
    }

    public I_S getISById(I_S.I_SKey i_sKey) {
        return i_sRepository.findById(i_sKey).orElse(null);
    }

    public I_S saveIS(I_S i_s) {
        return i_sRepository.save(i_s);
    }

    public void deleteIS(I_S.I_SKey i_sKey) {
        i_sRepository.deleteById(i_sKey);
    }
}
