package com.example.mysite.service;

import com.example.mysite.classes.U_I_T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mysite.repository.U_I_TRepository;

import java.util.List;
import java.util.Optional;

@Service
public class U_I_TService {

    @Autowired
    private U_I_TRepository u_i_tRepository;

    public U_I_T save(U_I_T u_i_t) {
        return u_i_tRepository.save(u_i_t);
    }

    public Optional<U_I_T> findById(int id) {
        return u_i_tRepository.findById(id);
    }

    public void deleteById(int id) {
        u_i_tRepository.deleteById(id);
    }

    public List<U_I_T> getAllUITs() {
        return u_i_tRepository.findAll();
    }
}
