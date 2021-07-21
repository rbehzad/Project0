package com.example.omarket.backend.data.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.omarket.backend.data.data.Dao.ProductDao;
import com.example.omarket.backend.data.data.Dao.UserDao;
import com.example.omarket.backend.data.data.entities.Product;
import com.example.omarket.backend.data.data.entities.RepoUser;

@Database(entities = {RepoUser.class, Product.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
}
