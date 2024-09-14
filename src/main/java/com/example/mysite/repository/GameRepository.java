package com.example.mysite.repository;

import com.example.mysite.classes.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("SELECT g FROM Game g WHERE g.hunter.user.user_id = ?1 OR g.survivor1.user.user_id = ?1 OR g.survivor2.user.user_id = ?1 OR g.survivor3.user.user_id = ?1 OR g.survivor4.user.user_id =?1")
    List<Game> findByUser_id(Integer user_id);
}
