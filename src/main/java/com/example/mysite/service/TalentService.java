package com.example.mysite.service;

import com.example.mysite.classes.Talent;
import com.example.mysite.repository.TalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentService {
    private TalentRepository talentRepository;

    @Autowired
    public TalentService(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    public Talent saveOrUpdateTalent(Talent talent) {
        return talentRepository.save(talent);
    }

    // Find all Talents
    public List<Talent> getAllTalents() {
        return talentRepository.findAll();
    }

    // Delete a Talent by its ID
    public void deleteTalentById(Integer talentId) {
        talentRepository.deleteById(talentId);
    }

    public List<Talent> getTalentsByCamp(String camp) {
        return talentRepository.findByCamp(camp);
    }
}
