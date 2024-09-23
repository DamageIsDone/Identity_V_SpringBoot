package com.example.mysite.controller;

import com.example.mysite.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @GetMapping("/recover")
    public User recoverPassword(
            @RequestParam("user_id")Integer user_id,
            @RequestParam("security_answer")String security_answer) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND security_answer = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{user_id, security_answer},
                new BeanPropertyRowMapper<>(User.class));
    }

    @GetMapping("/login")
    public User getUser(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password) {
        String sql = "SELECT * FROM users WHERE phone = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{phone, password},
                new BeanPropertyRowMapper<>(User.class));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        String sql = "INSERT INTO users (username, password, phone, " +
                "security_question, security_answer, is_manager) " +
                "VALUES (?, ?, ?, ?, ?, false)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getPhone(),
                user.getSecurity_question(), user.getSecurity_answer());
        return user;
    }

    @PutMapping("/update")
    public User exchangePassword(
            @RequestParam("user_id")Integer user_id,
            @RequestParam("new_password")String new_password) {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, new_password, user_id);
        User user = jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE user_id = ?",
                new Object[]{user_id},
                new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
