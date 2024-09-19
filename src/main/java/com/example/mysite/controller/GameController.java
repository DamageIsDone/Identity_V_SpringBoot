package com.example.mysite.controller;

import com.example.mysite.classes.Game;
import com.example.mysite.classes.Talent;
import com.example.mysite.classes.U_I_T;
import com.example.mysite.service.GameService;
import com.example.mysite.classes.Identity;
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

    @PutMapping("/update")
    public Identity updateIdentity(
            @RequestParam int game_id,
            @RequestParam int old_identity_id,
            @RequestParam int new_identity_id) {

        // 查询旧身份
        String sqlFetchOldIdentity = "SELECT * FROM U_I_T WHERE identity_id = ? AND id IN (" +
                "SELECT hunter_id FROM Game WHERE game_id = ? " +
                "UNION " +
                "SELECT survivor1_id FROM Game WHERE game_id = ? " +
                "UNION " +
                "SELECT survivor2_id FROM Game WHERE game_id = ? " +
                "UNION " +
                "SELECT survivor3_id FROM Game WHERE game_id = ? " +
                "UNION " +
                "SELECT survivor4_id FROM Game WHERE game_id = ?)";

        Identity oldIdentity = jdbcTemplate.queryForObject(sqlFetchOldIdentity, new Object[]{old_identity_id, game_id, game_id, game_id, game_id, game_id}, new BeanPropertyRowMapper<>(Identity.class));

        if (oldIdentity == null) {
            return null; // 处理可以根据你的需求调整
        }

        // 使用临时表进行更新
        String sqlUpdate = "UPDATE U_I_T SET identity_id = ? WHERE id IN ( " +
                "SELECT id FROM ( " +
                "SELECT hunter_id AS id FROM Game WHERE game_id = ? AND hunter_id IN (SELECT id FROM U_I_T WHERE identity_id = ?) " +
                "UNION " +
                "SELECT survivor1_id FROM Game WHERE game_id = ? AND survivor1_id IN (SELECT id FROM U_I_T WHERE identity_id = ?) " +
                "UNION " +
                "SELECT survivor2_id FROM Game WHERE game_id = ? AND survivor2_id IN (SELECT id FROM U_I_T WHERE identity_id = ?) " +
                "UNION " +
                "SELECT survivor3_id FROM Game WHERE game_id = ? AND survivor3_id IN (SELECT id FROM U_I_T WHERE identity_id = ?) " +
                "UNION " +
                "SELECT survivor4_id FROM Game WHERE game_id = ? AND survivor4_id IN (SELECT id FROM U_I_T WHERE identity_id = ?) " +
                ") AS tempTable)";

        jdbcTemplate.update(sqlUpdate, new_identity_id,
                game_id, old_identity_id,
                game_id, old_identity_id,
                game_id, old_identity_id,
                game_id, old_identity_id,
                game_id, old_identity_id);

        return oldIdentity;
    }

}
