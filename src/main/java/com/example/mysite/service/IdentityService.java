package com.example.mysite.service;

import com.example.mysite.classes.Identity;
import com.example.mysite.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityService {

    @Autowired
    private IdentityRepository identityRepository;

    public List<Identity> findAll() {
        return identityRepository.findAll();
    }

    public Optional<Identity> findById(Integer id) {
        return identityRepository.findById(id);
    }

    public Identity save(Identity identity) {
        return identityRepository.save(identity);
    }

    public void deleteById(Integer id) {
        identityRepository.deleteById(id);
    }

    public long countAllIdentities() {
        return identityRepository.count();
    }

    public Identity getIdentityById(Integer id) {
        return identityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identity not found with id: " + id));
    }
}
