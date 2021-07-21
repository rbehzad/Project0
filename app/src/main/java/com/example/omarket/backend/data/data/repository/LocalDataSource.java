package com.example.omarket.backend.data.data.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.omarket.backend.data.data.AppDataBase;
import com.example.omarket.backend.data.data.entities.Product;
import com.example.omarket.backend.data.data.entities.RepoUser;

import java.util.List;

public class LocalDataSource {
    private AppDataBase db;

    public LocalDataSource(Context context) {
        db = Room.databaseBuilder(context,
                AppDataBase.class, "todo_database").build();
    }
    ///////////
    // get all user
    public List<RepoUser> getAllUsers() {
        return db.userDao().getAll();
    }
    // save new user
    public void insertUser(String name, String emailAddress, String password, String phoneNumber, String userType) {
        db.userDao().insert(name, emailAddress, password, phoneNumber, userType);
    }
    // search a user by its emailAddress
    public RepoUser searchUser(String userEmailAddress) {
        return db.userDao().loadByEmail(userEmailAddress);
    }
    //////////
    // get all product
    public List<Product> getAllProducts() {
        return db.productDao().getAll();
    }
    // save new product
    public void insertProduct(String title, String description, String cost, String imagePath, String sellerEmail) {
//        db.productDao().insert(title, description, cost, imagePath, sellerEmail);
    }
    // search a product by its name
    public Product searchProduct(String productName) {
        return db.productDao().loadByName(productName);
    }
}
