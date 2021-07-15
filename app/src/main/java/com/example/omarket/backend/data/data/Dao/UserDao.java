package com.example.omarket.backend.data.data.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.omarket.backend.data.data.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("INSERT INTO User VALUES(:id, :name, :emailAddress, :password, :phoneNumber, :userType)")
    void insert(int id, String name, String emailAddress, String password, String phoneNumber, String userType);

    // get user info by searching its emailAddress
    @Query("SELECT * FROM User WHERE emailAddress IN (:userEmailAddress) ")
    User loadByEmail(String userEmailAddress);
    // get all user
    @Query("SELECT * FROM User")
    List<User> getAll();
    // save users
    @Insert
    void insertAll(User...users);
    // delete users
    @Delete
    void delete(User user);
}
