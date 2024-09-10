package com.example.mysite.controller;

import com.example.mysite.classes.Distinction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distinctions")
public class DistinctionController {
    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Distinction> getAllDistinctions() {
        String sql = "SELECT * FROM distinction";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Distinction.class));
    }

    @PostMapping
    public Distinction createDistinction(@RequestBody Distinction distinction) {
        String sql = "INSERT INTO Distinction (name, description, picture) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, distinction.getName(), distinction.getDescription(), distinction.getPicture());
        return distinction;
    }

    @DeleteMapping("/delete")
    public Distinction deleteDistinction(@RequestParam Integer distinction_id) {
        String selectSql = "SELECT * FROM Distinction WHERE distinction_id = ?";
        Distinction distinction = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Distinction.class), distinction_id);

        if (distinction != null) {
            String deleteSql = "DELETE FROM Distinction WHERE distinction_id = ?";
            jdbcTemplate.update(deleteSql, distinction_id);
        }

        return  distinction;
    }
}
