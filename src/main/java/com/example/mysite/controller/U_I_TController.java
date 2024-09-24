package com.example.mysite.controller;

import com.example.mysite.classes.Distinction;
import com.example.mysite.classes.Talent;
import com.example.mysite.classes.U_I_T;
import com.example.mysite.service.U_I_TService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/uits")
public class U_I_TController {
    @Autowired
    private U_I_TService u_i_tService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<U_I_T> getAllUITs() {
        return u_i_tService.getAllUITs();
    }

    @GetMapping("/user")
    public List<U_I_T> getUITByU(@RequestParam("user_id") Integer user_id) {
        return u_i_tService.getUITByU(user_id);
    }

    @GetMapping("/view_talent")
    public List<Talent> getTalent(@RequestParam("id")Integer id) {
        String sql1 = "SELECT * FROM Talent WHERE talent_id = " +
                "( SELECT talent1_id FROM U_I_T WHERE id = ? ) ";
        List<Talent> talents1 = jdbcTemplate.query(sql1, new BeanPropertyRowMapper<>(Talent.class), id);

        String sql2 = "SELECT * FROM Talent WHERE talent_id = " +
                "( SELECT talent2_id FROM U_I_T WHERE id = ? ) ";
        List<Talent> talents2 = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Talent.class), id);

        talents1.addAll(talents2);
        return talents1;
    }

    @PutMapping("/update")
    public U_I_T updateUIT(
            @RequestParam("id") Integer id,
            @RequestParam("old_talent_id") Integer old_talent_id,
            @RequestParam("new_talent_id") Integer new_talent_id) {
        String selectSql1 = "SELECT * FROM U_I_T WHERE id = ? AND talent1_id = ?";
        List<U_I_T> results1 = jdbcTemplate.query(selectSql1, new BeanPropertyRowMapper<>(U_I_T.class), id, old_talent_id);

        String selectSql2 = "SELECT * FROM U_I_T WHERE id = ? AND talent2_id = ?";
        List<U_I_T> results2 = jdbcTemplate.query(selectSql2, new BeanPropertyRowMapper<>(U_I_T.class), id, old_talent_id);

        if (!results1.isEmpty()) {
            U_I_T u_i_t1 = results1.get(0);
            String updateSql1 = "UPDATE U_I_T SET talent1_id = ? WHERE id = ? AND talent1_id = ?";
            jdbcTemplate.update(updateSql1, new_talent_id, id, old_talent_id);
            return u_i_t1;
        } else if (!results2.isEmpty()) {
            U_I_T u_i_t2 = results2.get(0);
            String updateSql2 = "UPDATE U_I_T SET talent2_id = ? WHERE id = ? AND talent2_id = ?";
            jdbcTemplate.update(updateSql2, new_talent_id, id, old_talent_id);
            return u_i_t2;
        } else {
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createUIT(
            @RequestParam("user_id")Integer user_id,
            @RequestParam("identity_id")Integer identity_id,
            @RequestParam("talent1_id")Integer talent1_id,
            @RequestParam("talent2_id")Integer talent2_id) {
        String sql = "INSERT INTO U_I_T (user_id, identity_id, talent1_id, talent2_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user_id, identity_id,
                talent1_id, talent2_id);

        // 获取新创建的 ID
        Integer newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        return ResponseEntity.status(201).body(newId);
    }

}
