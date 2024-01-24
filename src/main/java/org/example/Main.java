package org.example;

import org.example.menu.*;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.dao.GameRepository;
import org.example.repository.dao.UserRepository;
import org.example.service.ConnectionSingleton;
import org.example.service.GameService;
import org.example.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionSingleton.getConnection();

            GameRepository gameRepository = new GameRepositoryImpl(connection);
            UserRepository userRepository = new UserRepositoryImpl(connection);
            GameService gameService = new GameService(gameRepository);
            UserService userService = new UserService(userRepository);
            UserMenu userMenu = new UserMenu(scanner, userService);
            WalletMenu walletMenu = new WalletMenu(scanner, userService);
            GameShopMenu gameShopMenu = new GameShopMenu(scanner, gameService, userService, walletMenu, userMenu);


            userMenu.run();
            gameShopMenu.run();
            connection.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
