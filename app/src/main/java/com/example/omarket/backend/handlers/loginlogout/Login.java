package com.example.omarket.backend.handlers.loginlogout;

import com.example.omarket.backend.user.User;

/**
 * Singleton class
 */
public class Login {

    private static Login login;

    private Login() {
        login = this;
    }

    public static Login getInstance() {
        if (login != null)
            return login;
        return new Login();
    }

    public User login() {
        return null;
    }
}
