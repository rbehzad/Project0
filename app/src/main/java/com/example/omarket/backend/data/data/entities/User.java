package com.example.omarket.backend.data.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public String emailAddress;

    public String password;

    public String phoneNumber;

    public UserType userType;


}
