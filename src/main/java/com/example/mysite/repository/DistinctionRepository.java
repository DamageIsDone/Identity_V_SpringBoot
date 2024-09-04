package com.example.mysite.repository;

import com.example.mysite.classes.Distinction;
import com.example.mysite.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistinctionRepository extends JpaRepository<User, Integer> {
}
