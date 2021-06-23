package com.example.omarket.backend.handlers.loginlogout;

import com.example.omarket.backend.handlers.dataHandler.DataManager;
import com.example.omarket.backend.user.User;

/**
 * Singleton class
 */
public class Login {

    private static Login login;
    private User user;
    private DataManager dataManager;

    private Login() {
        dataManager = DataManager.getInstance();
        login = this;
    }

    public static Login getInstance() {
        if (login != null)
            return login;
        return new Login();
    }

    public User login(User user) {
        this.user = dataManager.findUser(user);
        return user;
    }
}
