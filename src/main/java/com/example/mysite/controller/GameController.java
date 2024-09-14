package com.example.mysite.controller;

import com.example.mysite.classes.Game;
import com.example.mysite.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/user")
    public List<Game> getGameByU(@RequestParam("user_id") Integer user_id) {
        return gameService.getGameByUser(user_id);
    }
}
