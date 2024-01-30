package org.example.repository.dao;

import org.example.model.Game;
import java.util.List;

public interface GameRepository {
    Game findById(int id);
    String findByName(String name);
    List<Game> findAll();
    int getNumberOfGames();
}
