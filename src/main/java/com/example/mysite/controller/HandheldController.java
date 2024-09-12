package com.example.mysite.controller;

import com.example.mysite.classes.Distinction;
import com.example.mysite.classes.Handheld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public ResponseEntity<Handheld> updateHandheld(@PathVariable Integer id, @RequestBody Handheld handheld) {
        String sqlCheck = "SELECT COUNT(*) FROM handheld WHERE handheld_id = ?";
        int count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{id}, Integer.class);
        if (count == 0) {
            return ResponseEntity.notFound().build();
        }
        String sqlUpdate = "UPDATE handheld SET name = ?, description = ?, picture = ? WHERE handheld_id = ?";
        jdbcTemplate.update(sqlUpdate, handheld.getName(), handheld.getDescription(), handheld.getPicture(), id);
        return ResponseEntity.ok(handheld);
    }

    @DeleteMapping("/delete")
    public Handheld deleteHandheld(@RequestParam Integer handheld_id) {
        String selectSql = "SELECT * FROM Handheld WHERE handheld_id = ?";
        Handheld handheld = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Handheld.class), handheld_id);

        if (handheld != null) {
            String deleteSql = "DELETE FROM Handheld WHERE handheld_id = ?";
            jdbcTemplate.update(deleteSql, handheld_id);
        }

        return handheld;
    }

    @DeleteMapping("/delete/i")
    public List<Handheld> deleteHandhelds(@RequestParam Integer identity_id) {
        String selectSql = "SELECT * FROM Handheld WHERE handheld_id IN " +
                "( SELECT handheld_id FROM I_H WHERE identity_id = ? )";
        List<Handheld> handhelds = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Handheld.class), identity_id);

        jdbcTemplate.update("SET foreign_key_checks = 0");
        String sql = "DELETE FROM Handheld WHERE handheld_id IN " +
                "( SELECT handheld_id FROM I_H WHERE identity_id = ? )";
        jdbcTemplate.update(sql, identity_id);
        jdbcTemplate.update("SET foreign_key_checks = 1");

        return handhelds;
    }

}
