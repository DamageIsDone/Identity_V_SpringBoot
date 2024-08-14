package com.example.mysite.repository;

import com.example.mysite.classes.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Integer> {
}