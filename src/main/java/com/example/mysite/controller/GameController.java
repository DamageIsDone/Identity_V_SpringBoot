package com.example.mysite.controller;

import com.example.mysite.classes.Game;
import com.example.mysite.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/result")
    public int getResult(@RequestParam("game_id") Integer game_id) {
        String sql = "SELECT (CASE WHEN result1 = TRUE THEN 1 ELSE 0 END + " +
                "CASE WHEN result2 = TRUE THEN 1 ELSE 0 END + " +
                "CASE WHEN result3 = TRUE THEN 1 ELSE 0 END + " +
                "CASE WHEN result4 = TRUE THEN 1 ELSE 0 END) AS true_count " +
                "FROM Game WHERE game_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{game_id}, Integer.class);
    }

    @DeleteMapping("/delete")
    public Game deleteGame(@RequestParam("game_id") Integer game_id) {
        String selectSql = "SELECT * FROM Game WHERE game_id = ?";
        Game game = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Game.class), game_id);

        if (game != null) {
            String sql = "DELETE FROM Game WHERE game_id = ?";
            jdbcTemplate.update(sql, game_id);
        }

        return game;
    }

}
