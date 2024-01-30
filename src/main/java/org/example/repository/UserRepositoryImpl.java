package org.example.repository;

import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findById(int userId) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM users WHERE id = ?" + userId
            );
            resultSet.next();

            return User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .birthday(resultSet.getDate("birthday"))
                    .amount(resultSet.getInt("amount"))
                    .nickname(resultSet.getString("nickname"))
                    .password(resultSet.getString("password"))
                    .build();

        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByNickname(String nickname) {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM users WHERE nickname = '" + nickname + "'"
            );

            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .birthday(resultSet.getDate("birthday"))
                        .amount(resultSet.getInt("amount"))
                        .nickname(resultSet.getString("nickname"))
                        .password(resultSet.getString("password"))
                        .build();
            } else {
                return null;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public User register(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(name, birthday, amount, nickname, password) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
            preparedStatement.setInt(3, user.getAmount());
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void recharge(int amount, int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET amount = ? WHERE  id = ?;"
            );
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> userGames(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT users_games.games_id FROM users_games WHERE users_games.users_id = ?"
            );
            preparedStatement.setInt(id, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> userGamesId = new ArrayList<>();

            while (resultSet.next()) {
                Integer gameId = resultSet.getInt("games_id");
                userGamesId.add(gameId);
            }

            return userGamesId;

        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addGame(int userId, int gameId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"users_games\"(users_id, games_id) VALUES (?, ?);"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}

