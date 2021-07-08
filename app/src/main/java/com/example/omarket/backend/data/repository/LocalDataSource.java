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
    ///////////
    // get all user
    public List<User> getAllUsers() {
        return db.userDao().getAll();
    }
    // save new user
    public void insertUser(int id, String name, String emailAddress, String password, String phoneNumber, String userType) {
        db.userDao().insert(id, name, emailAddress, password, phoneNumber, userType);
    }
    // search a user by its emailAddress
    public User searchUser(String userEmailAddress) {
        return db.userDao().loadByEmail(userEmailAddress);
    }
    //////////
    // get all product
    public List<Product> getAllProducts() {
        return db.productDao().getAll();
    }
    // save new product
    public void insertProduct(int id, String name, String info, String imagePath, String sellerName, String sellerId) {
        db.productDao().insert(id, name, info, imagePath, sellerName, sellerId);
    }
    // search a product by its name
    public Product searchProduct(String productName) {
        return db.productDao().loadByName(productName);
    }
}
