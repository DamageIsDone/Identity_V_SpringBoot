package com.example.mysite.repository;

import com.example.mysite.classes.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, Integer> {
    Identity findByName(String name);
}
