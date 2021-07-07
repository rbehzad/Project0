package com.example.omarket.backend.data.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.omarket.backend.data.entities.Product;
import com.example.omarket.backend.data.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("INSERT INTO User VALUES(:id, :name, :emailAddress, :password, :phoneNumber, :userType)")
    void insert(int id, String name, String emailAddress, String password, String phoneNumber, String userType);

    // get product info by searching its name
    @Query("SELECT * FROM User WHERE emailAddress IN (:userEmailAddress) ")
    List<User> loadAllByIds(int[] userEmailAddress);

    @Insert
    void insertAll(User...users);

    @Delete
    void delete(User user);
}
