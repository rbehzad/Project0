package com.example.omarket.backend.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    public int id;

    public String name;

    public String info;

    public String imagePath;

    public String sellerName;

    public int sellerId;
}
