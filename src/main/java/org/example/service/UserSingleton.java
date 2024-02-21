package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.User;

@Setter
@Getter
public class UserSingleton {
    private static UserSingleton instance;
    private User user;

    private UserSingleton() {
        this.user = User.builder().build();
    }
    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

}