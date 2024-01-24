package org.example.repository.dao;

import org.example.model.User;
import java.util.List;

public interface UserRepository {
    User register(User user);
    User findById(int id);
    User findByNickname(String nickname);
    List<Integer> userGames(int id);
    void recharge(int amount, int userId);
    // void recharge(User user);
    void addGame(int userId, int gameId);
}
