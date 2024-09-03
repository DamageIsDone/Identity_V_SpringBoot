package com.example.mysite.controller;

import com.example.mysite.classes.Talent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talents")
public class TalentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get a Talent by its ID
    @GetMapping("/{id}")
    public Talent getTalentById(@PathVariable Integer id) {
        String sql = "SELECT * FROM Talent WHERE talent_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(Talent.class));
    }

    // Create a new Talent
    @PostMapping
    public Talent createTalent(@RequestBody Talent talent) {
        String sql = "INSERT INTO Talent (name, description, effect, camp, picture) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, talent.getName(), talent.getDescription(), talent.getEffect(), talent.getCamp(), talent.getPicture());
        return talent; // Return the created Talent object
    }

    // Get all Talents
    @GetMapping
    public List<Talent> getAllTalents() {
        String sql = "SELECT * FROM Talent";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Talent.class));
    }

    // Delete a Talent by its ID
    @DeleteMapping("/{id}")
    public void deleteTalentById(@PathVariable Integer id) {
        String sql = "DELETE FROM Talent WHERE talent_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
