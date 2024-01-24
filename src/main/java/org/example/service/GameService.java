package org.example.service;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;
import java.util.List;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {

        this.gameRepository = gameRepository;
    }


    public Game findById(int id) {
        return this.gameRepository.findById(id);
    }

    public String findByName(String name) {
        return this.gameRepository.findByName(name);
    }

    public List<Game> showAll() {

        return this.gameRepository.showAll();
    }
    public int getNumberOfGames() {
        return this.gameRepository.getNumberOfGames();
    }
}