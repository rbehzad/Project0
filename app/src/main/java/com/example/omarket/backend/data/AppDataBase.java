package com.example.omarket.backend.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.omarket.backend.data.Dao.ProductDao;
import com.example.omarket.backend.data.Dao.UserDao;
import com.example.omarket.backend.data.entities.Product;
import com.example.omarket.backend.data.entities.User;

@Database(entities = {User.class, Product.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
}
