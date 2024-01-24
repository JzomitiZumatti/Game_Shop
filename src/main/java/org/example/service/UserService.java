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

        return this.userRepository.findById(id);
    }

    public User findByNickname(String nickname) {

        return this.userRepository.findByNickname(nickname);
    }

    public User register(User user) {

        return this.userRepository.register(user);
    }

    /*public void recharge(User user) {

        userRepository.recharge(user);
    }*/
    public void recharge(int amount, int userId) {

        userRepository.recharge(amount, userId);
    }

    public List<Integer> userGames(int id) {
        return this.userRepository.userGames(id);
    }

    public void addGame(int userId, int gameId) {
        userRepository.addGame(userId, gameId);
    }
}
