package com.example.mysite.controller;

import com.example.mysite.classes.Distinction;
import com.example.mysite.classes.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Skill> getAllSkills() {
        String sql = "SELECT * FROM skill";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Skill.class));
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        String sql = "INSERT INTO Skill (name, description, picture) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, skill.getName(), skill.getDescription(), skill.getPicture());
        return skill;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer id, @RequestBody Skill skill) {
        String sqlCheck = "SELECT COUNT(*) FROM skill WHERE skill_id = ?";
        int count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{id}, Integer.class);
        if (count == 0) {
            return ResponseEntity.notFound().build();
        }
        String sqlUpdate = "UPDATE skill SET name = ?, description = ?, picture = ? WHERE skill_id = ?";
        jdbcTemplate.update(sqlUpdate, skill.getName(), skill.getDescription(), skill.getPicture(), id);
        return ResponseEntity.ok(skill);
    }

    @DeleteMapping("/delete")
    public Skill deleteSkill(@RequestParam Integer skill_id) {
        String selectSql = "SELECT * FROM Skill WHERE skill_id = ?";
        Skill skill = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Skill.class), skill_id);

        if (skill != null) {
            String deleteSql = "DELETE FROM Skill WHERE skill_id = ?";
            jdbcTemplate.update(deleteSql, skill_id);
        }

        return skill;
    }

    @DeleteMapping("/delete/i")
    public List<Skill> deleteSkills(@RequestParam Integer identity_id) {
        String selectSql = "SELECT * FROM Skill WHERE skill_id IN " +
                "( SELECT skill_id FROM I_S WHERE identity_id = ? )";
        List<Skill> skills = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Skill.class), identity_id);

        jdbcTemplate.update("SET foreign_key_checks = 0");
        String sql = "DELETE FROM Skill WHERE skill_id IN " +
                "( SELECT skill_id FROM I_S WHERE identity_id = ? )";
        jdbcTemplate.update(sql, identity_id);
        jdbcTemplate.update("SET foreign_key_checks = 1");

        return skills;
    }

}
