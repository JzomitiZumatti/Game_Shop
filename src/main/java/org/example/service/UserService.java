package org.example.service;

import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public User register(User user) {
        return userRepository.register(user);
    }

    public void recharge(int amount, int userId) {
        userRepository.recharge(amount, userId);
    }

    public List<Integer> userGames(int id) {
        return userRepository.userGames(id);
    }

    public void addGame(int userId, int gameId) {
        userRepository.addGame(userId, gameId);
    }
}
