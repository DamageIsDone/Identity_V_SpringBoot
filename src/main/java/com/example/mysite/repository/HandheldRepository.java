package com.example.mysite.repository;

import com.example.mysite.classes.Handheld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandheldRepository extends JpaRepository<Handheld, Integer> {
}
