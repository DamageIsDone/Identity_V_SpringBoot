package com.example.mysite.service;

import com.example.mysite.classes.Game;
import com.example.mysite.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGameByUser(Integer user_id) {
        return gameRepository.findByUser_id(user_id);
    }
}
