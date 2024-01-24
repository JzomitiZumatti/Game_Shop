package org.example.repository;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    private final Connection connection;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Game findById(int id) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM games WHERE id = " + id);
            resultSet.next();

            return Game.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .release_date(resultSet.getString("release_date"))
                    .rating(resultSet.getInt("rating"))
                    .cost(resultSet.getInt("cost"))
                    .description(resultSet.getString("description"))
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
    public String findByName(String name) {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM games WHERE name = '" + name + "'");
            String result;
            if (resultSet.next()) {
                result = resultSet.getString("name");
            } else {
                result = null;
            }
            return result;
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
    public List<Game> showAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM games");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Game> games = new ArrayList<>();

            while (resultSet.next()) {
                Game game = Game.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .release_date(resultSet.getString("release_date"))
                        .rating(resultSet.getInt("rating"))
                        .cost(resultSet.getInt("cost"))
                        .description(resultSet.getString("description"))
                        .build();
                games.add(game);
            }

            return games;

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
    public int getNumberOfGames() {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM games");
            int numberOfGames = 0;
            while (resultSet.next()) {
                numberOfGames = resultSet.getInt(1);
            }

            return numberOfGames;

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
