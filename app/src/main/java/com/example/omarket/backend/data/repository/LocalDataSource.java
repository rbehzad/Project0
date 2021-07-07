package com.example.omarket.backend.data.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.omarket.backend.data.AppDataBase;
import com.example.omarket.backend.data.entities.Product;
import com.example.omarket.backend.data.entities.User;

import java.util.List;

public class LocalDataSource {
    private AppDataBase db;

    public LocalDataSource(Context context) {
        db = Room.databaseBuilder(context,
                AppDataBase.class, "todo_database").build();
    }
    // User methods
    public List<User> getAllUsers() {
        return db.userDao().getAll();
    }

    public void insertUser(User user) { // save new user
        db.userDao().insertAll(user);
    }
    // Product methods
    public List<Product> getAllProducts() {
        return db.productDao().getAll();
    }
}
