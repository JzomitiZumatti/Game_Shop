package org.example.menu;

import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserSingleton;
import java.util.List;
import java.util.Scanner;

public class UserMenu {

    private final Scanner scanner;
    private final UserService userService;
    private final UserSingleton userSingleton;
    private String choice;

    public UserMenu(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
        this.userSingleton = UserSingleton.getInstance();
    }

    public User getCurrentUser() {
        return userSingleton.getUser();
    }

    public void run() {
        System.out.println(Messages.GREETINGS);
        handleAccountExistence();
    }

    private void handleAccountExistence() {
        String response;
        do {
            System.out.println("Do you have an account? (y/n): ");
            response = scanner.nextLine();
        } while (!isValidResponse(response));

        if (response.equalsIgnoreCase("y")) {
            signIn();
        } else {
            handleNoAccount();
        }
    }

    private boolean isValidResponse(String input) {
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) return true;
        else {
            System.err.println(Messages.INVALID_INPUT + "'y' or 'n'.");
            return false;
        }
    }

    private void signIn() {
        System.out.println("Enter your username/nickname: ");
        String nickname = scanner.next();

        userSingleton.setUser(userService.findByNickname(nickname));

        if (isUserExist()) {
            authenticateUser();
        } else {
            handleAuthenticationFailure();
        }
    }

    private void handleNoAccount() {
        String registerResponse;

        do {
            System.out.println("Would you like to register? (y/n): ");
            registerResponse = scanner.nextLine();
        } while (!registerResponse.equalsIgnoreCase("y") && !registerResponse.equalsIgnoreCase("n"));

        if (registerResponse.equalsIgnoreCase("y")) {
            signUp();
            handleLoginAfterRegistrationOrExit();
        } else {
            exit();
        }
    }

    private void handleLoginAfterRegistrationOrExit() {
        String loginResponse;

        do {
            System.out.println("Would you like to log in now? (y/n): ");
            loginResponse = scanner.nextLine();
        } while (!loginResponse.equalsIgnoreCase("y") && !loginResponse.equalsIgnoreCase("n"));

        if (loginResponse.equalsIgnoreCase("y")) {
            signIn();
        } else {
            exit();
        }
    }

    private boolean isUserExist() {
        return userSingleton.getUser() != null;
    }

    private void authenticateUser() {
        System.out.println("Enter your password: ");
        String password = scanner.next();
        while (!password.equals(getCurrentUser().getPassword())) {
            System.err.println(Messages.WRONG_PASSWORD);
            password = scanner.next();
        }
        System.out.println(Messages.SUCCESSFUL_SIGN_IN + userSingleton.getUser().getNickname() + ".");
    }

    private void handleAuthenticationFailure() {
        while (!isUserExist()) {
            System.err.println(Messages.USER_NOT_FOUND + "You can: 1. try again or 2. register.");
            choice = getValidChoice();
            if (choice.equals("1")) {
                signIn();
            } else if (choice.equals("2")) {
                signUp();
                handleLoginAfterRegistrationOrExit();
                break;
            }
        }
    }

    private void signUp() {
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your birthday: ");
        String birthday = scanner.next();
        System.out.println("Enter your username/nickname: ");
        String nickname = scanner.next();
        System.out.println("Enter your password: ");
        String password = scanner.next();

        User newUser = User.builder()
                .name(name)
                .birthday(birthday)
                .amount(0)
                .nickname(nickname)
                .password(password)
                .build();

        userService.register(newUser);
        userSingleton.setUser(newUser);
        scanner.nextLine();
        System.out.println(Messages.SUCCESSFUL_SIGN_UP);
    }

    private String getValidChoice() {
        choice = scanner.next();
        while (!checkChoice()) {
            System.err.println(Messages.INVALID_INPUT + "1. try again or 2. register.");
            choice = scanner.next();
        }
        return choice;
    }

    private boolean checkChoice() {
        List<String> correctChoices = List.of("1", "2");
        return correctChoices.stream().anyMatch(element -> element.equals(choice));
    }

    public void exit() {
        if (isUserExist()) System.out.println("Thank you for using our services, " + getCurrentUser().getNickname() +
                "!\nHave a great day!");
        else {
            System.out.println("Thank you for using our services!\nHave a great day!");
        }
        userSingleton.setUser(null);
    }
}
