package com.example.omarket.backend.data.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public String description;

    public String cost;

    public String imagePath;

    public String sellerEmail;

}
