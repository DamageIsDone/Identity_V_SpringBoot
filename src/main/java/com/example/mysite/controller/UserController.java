package com.example.mysite.controller;

import com.example.mysite.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username},
                new BeanPropertyRowMapper<>(User.class));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        String sql = "INSERT INTO users (username, password, phone, security_question, security_answer) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getSecurity_question(), user.getSecurity_answer());
        return user; // Return the created user object
    }

    @GetMapping
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
