package com.example.omarket.backend.data.entities;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.omarket.backend.handlers.exepstions.ExceptionChecker;
import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.user.UserType;
@Entity
public class User {
    @PrimaryKey
    public int id;

    public String name;

    public String emailAddress;

    public String password;

    public String phoneNumber;

    public UserType userType;


}
