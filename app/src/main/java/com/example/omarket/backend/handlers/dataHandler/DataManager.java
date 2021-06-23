package com.example.omarket.backend.handlers.dataHandler;

import com.example.omarket.backend.user.User;

public class DataManager {

    private static DataManager dataManager;

    private DataManager() {
        dataManager = this;
    }

    public static DataManager getInstance() {
        if (dataManager != null)
            return dataManager;
        else
            return new DataManager();
    }

    public User findUser(User user) {

        return null;
    }

}
