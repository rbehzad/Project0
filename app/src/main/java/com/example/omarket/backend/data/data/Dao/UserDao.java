package com.example.omarket.backend.data.data.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.omarket.backend.data.data.entities.RepoUser;

import java.util.List;

@Dao
public interface UserDao {
    @Query("INSERT INTO RepoUser(name, emailAddress, password, phoneNumber, userType) VALUES(:name, :emailAddress, :password, :phoneNumber, :userType)")
    void insert(String name, String emailAddress, String password, String phoneNumber, String userType);

    // get user info by searching its emailAddress
    @Query("SELECT * FROM RepoUser WHERE emailAddress IN (:userEmailAddress) ")
    RepoUser loadByEmail(String userEmailAddress);
    // get all user
    @Query("SELECT * FROM RepoUser")
    List<RepoUser> getAll();
    // save users
    @Insert
    void insertAll(RepoUser... repoUsers);
    // delete users
    @Delete
    void delete(RepoUser repoUser);
}
