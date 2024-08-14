package com.example.mysite.repository;

import com.example.mysite.classes.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
}