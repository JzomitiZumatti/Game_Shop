package org.example.menu;

public enum Messages {
    GREETINGS("""
            Welcome to our Game Shop!
            We're thrilled to have you here. Whether you're a gaming enthusiast or just exploring,
            we hope you discover something special that excites you.
            To get started, sign in to your account for a personalized shopping experience.
            """),
    SHOW_SHOP_MENU("""
                    1. Show all games;
                    2. Buy game;
                    3. Show my games collection;
                    4. Show my balance;
                    5. Recharge balance;
                    0. Exit.
                Choose an option:"""),
    WRONG_PASSWORD("Wrong password. Try again:"),
    INVALID_INPUT("Invalid input. Please enter "),
    SUCCESSFUL_SIGN_IN("Login successful! Welcome back, "),
    SUCCESSFUL_SIGN_UP("Registered successfully."),
    USER_NOT_FOUND("User with this nickname not found."),
    WRONG_OPTION("You chose the wrong option! Try again:"),
    REJECT_RECHARGE("You are welcome to recharge your wallet in any time!"),
    GAME_ALREADY_BOUGHT("You have been already purchased this game and it is in your collection!"),
    NOT_ENOUGH_MONEY("On your balance doesn't enough money! Do you want to recharge your balance?"),
    REJECT_GAME_PURCHASE("Maybe next time you find something interesting for you."),
    CURRENT_BALANCE("Your (current) balance is:"),
    CHOOSE_GAME_NUMBER("Choose the number of the game which you want to buy:");

    private final String message;

    Messages(final String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return message;
    }
}
