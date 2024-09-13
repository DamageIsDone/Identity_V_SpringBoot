package com.example.mysite.controller;

import com.example.mysite.classes.U_I_T;
import com.example.mysite.service.U_I_TService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
