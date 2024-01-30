package org.example.menu;

import org.example.model.Game;
import org.example.service.GameService;
import org.example.service.UserService;
import org.example.service.UserSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameShopMenu {

    private final Scanner scanner;
    private final GameService gameService;
    private final UserService userService;
    private final UserSingleton userSingleton;
    private final UserMenu userMenu;
    private final WalletMenu walletMenu;
    private boolean isOn = true;

    public GameShopMenu(Scanner scanner, GameService gameService, UserService userService, WalletMenu walletMenu, UserMenu userMenu) {
        this.scanner = scanner;
        this.gameService = gameService;
        this.userService = userService;
        this.userSingleton = UserSingleton.getInstance();
        this.walletMenu = walletMenu;
        this.userMenu = userMenu;
    }

    public void run() {
        while (isOn) {
            System.out.println(Messages.SHOW_SHOP_MENU);
            switch (menuResponse()) {
                case "1" -> showAllGames();
                case "2" -> purchaseGameMenu();
                case "3" -> showUserGameCollection();
                case "4" -> System.out.println(Messages.CURRENT_BALANCE + " " + walletMenu.showBalance() + "$.");
                case "5" -> walletMenu.recharge();
                case "0" -> {
                    isOn = false;
                    userMenu.exit();
                }
            }
        }
    }

    private String menuResponse() {
        List<String> correctResponse = List.of("1", "2", "3", "4", "5", "0");
        String menuResponse = scanner.next();
        while (!checkResponse(menuResponse, correctResponse)) {
            System.err.println(Messages.WRONG_OPTION);
            menuResponse = scanner.next();
        }
        return menuResponse;
    }

    private boolean checkResponse(String gameResponse, List<String> correctResponse) {
        return correctResponse.stream().
                anyMatch(element -> element.equals(gameResponse));
    }

    private void showAllGames() {
        gameService.showAll().forEach(System.out::println);
    }

    private void purchaseGameMenu() {
        String gameResponse;
        List<String> correctResponse = new ArrayList<>();
        for (int i = 1; i <= gameService.getNumberOfGames(); i++) {
            correctResponse.add(String.valueOf(i));
        }
        showAllGames();
        System.out.println(Messages.CHOOSE_GAME_NUMBER);
        gameResponse = scanner.next();
        while (!checkResponse(gameResponse, correctResponse)) {
            System.err.println(Messages.WRONG_OPTION);
            gameResponse = scanner.next();
        }
        if (isEnoughMoney(gameResponse) && !isGameAlreadyBought(gameResponse)) {
            System.out.println("Do you really want to buy " + gameService.findById(Integer.parseInt(gameResponse)).getName() + "?");
            if (hasResponse()) purchaseGame(gameResponse);
            else System.out.println(Messages.REJECT_GAME_PURCHASE);
        } else if (!isEnoughMoney(gameResponse) && !isGameAlreadyBought(gameResponse)) {
            System.err.println(Messages.NOT_ENOUGH_MONEY);
            if (hasResponse()) walletMenu.recharge();
            else System.out.println(Messages.REJECT_RECHARGE);
        } else if ((isEnoughMoney(gameResponse) || !isEnoughMoney(gameResponse)) && isGameAlreadyBought(gameResponse)) {
            System.err.println(Messages.GAME_ALREADY_BOUGHT);
        }
    }

    private boolean isEnoughMoney(String gameResponse) {
        return gameService.findById(Integer.parseInt(gameResponse)).getCost() <= walletMenu.showBalance();
    }

    private boolean isGameAlreadyBought(String gameResponse) {
        List<Game> ownedGames = new ArrayList<>();
        for (Integer id : userService.userGames(userSingleton.getUser().getId())) {
            ownedGames.add(gameService.findById(id));
        }
        return ownedGames.stream().
                anyMatch(game -> game.getName().equals(gameService.findByName(gameService.findById(Integer.parseInt(gameResponse)).getName())));
    }

    private boolean hasResponse() {
        String response;

        do {
            System.out.print("(y/n): ");
            response = scanner.next().trim();
        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));

        return response.equalsIgnoreCase("y");
    }

    private void purchaseGame(String gameResponse) {
        userService.addGame(userSingleton.getUser().getId(), Integer.parseInt(gameResponse));
        walletMenu.purchase(gameService.findById(Integer.parseInt(gameResponse)).getCost());
        System.out.println("The game \"" + gameService.findById(Integer.parseInt(gameResponse)).getName() +
                ", was successfully added in your collection.");
    }

    private void showUserGameCollection() {
        List<Game> ownedGames = new ArrayList<>();
        for (Integer id : userService.userGames(userSingleton.getUser().getId())) {
            ownedGames.add(gameService.findById(id));
        }
        ownedGames.forEach(System.out::println);
    }

}
