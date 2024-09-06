package com.example.mysite.controller;

import com.example.mysite.classes.Handheld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/handhelds")
public class HandheldController {
    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Handheld> getAllHandhelds() {
        String sql = "SELECT * FROM handheld";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Handheld.class));
    }

    @PostMapping
    public Handheld createHandheld(@RequestBody Handheld handheld) {
        String sql = "INSERT INTO Handheld (name, description, picture) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, handheld.getName(), handheld.getDescription(), handheld.getPicture());
        return handheld;
    }
}
