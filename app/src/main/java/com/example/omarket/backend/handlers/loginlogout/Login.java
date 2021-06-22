package com.example.omarket.backend.handlers.loginlogout;

import com.example.omarket.backend.user.User;

/**
 * Singleton class
 */
public class Login {

    private static Login login;
    private static User user;

    private Login() {
        login = this;
    }

    public static Login getInstance() {
        if (login != null)
            return login;
        return new Login();
    }

    public User login(User user) {


        // if every things okay
        Login.user = user;
        return user;
    }
}
