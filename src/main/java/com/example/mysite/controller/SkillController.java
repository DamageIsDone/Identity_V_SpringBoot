package com.example.mysite.controller;

import com.example.mysite.classes.Skill;
import org.springframework.beans.factory.annotation.Autowired;
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

}
