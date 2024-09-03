package com.example.mysite.repository;

import com.example.mysite.classes.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Integer> {
    Talent findByName(String name);

    List<Talent> findByCamp(String camp);
}
