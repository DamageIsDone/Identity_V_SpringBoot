package com.example.mysite.service;

import com.example.mysite.classes.I_H;
import com.example.mysite.repository.I_HRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class I_HService {
    @Autowired
    private I_HRepository i_hRepository;

    public  List<I_H> getALLIHs() {
        return i_hRepository.findAll();
    }

    public I_H getIHById(I_H.I_HKey i_hKey) {
        return i_hRepository.findById(i_hKey).orElse(null);
    }

    public I_H saveIH(I_H i_h) {
        return i_hRepository.save(i_h);
    }

    public void deleteIH(I_H.I_HKey i_hKey) {
        i_hRepository.deleteById(i_hKey);
    }
}
