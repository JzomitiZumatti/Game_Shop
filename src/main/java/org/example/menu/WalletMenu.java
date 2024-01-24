package org.example.menu;

import org.example.service.UserService;
import org.example.service.UserSingleton;
import java.util.Scanner;

public class WalletMenu {
    private final Scanner scanner;
    private final UserService userService;
    private final UserSingleton userSingleton;

    public WalletMenu(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
        this.userSingleton = UserSingleton.getInstance();
    }

    public void recharge() {
        System.out.println("Enter amount: ");
        int amountAfterRecharge = scanner.nextInt() + userSingleton.getUser().getAmount();
        userSingleton.getUser().setAmount(amountAfterRecharge);
        userService.recharge(amountAfterRecharge, userSingleton.getUser().getId());
        System.out.println("Recharge successful. Your (current) balance is : " + userSingleton.getUser().getAmount() + "$.");
    }

    public void purchase(int amount) {
        int amountAfterPurchase = userSingleton.getUser().getAmount() - amount;
        userSingleton.getUser().setAmount(amountAfterPurchase);
        userService.recharge(amountAfterPurchase, userSingleton.getUser().getId());
    }

    public int showBalance() {
        return userSingleton.getUser().getAmount();
    }
}
