package com.example.omarket.backend.product;


import androidx.annotation.Nullable;

import com.example.omarket.backend.user.User;

import java.util.ArrayList;

public class Product {
    public static ArrayList<Product> allProducts = new ArrayList<>();
    public String name;
    public String price;
    public User user;
    public String userEmail;
    public String image;
    public String id;
    public String description;

    public Product(){

    }

    public Product(String name, String price,  String image, String description, String userEmail) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.userEmail = userEmail;
        this.description = description;
    }

    @Override
    public boolean equals(@Nullable @org.jetbrains.annotations.Nullable Object obj) {
        Product pr = (Product) obj;
        return pr.id.trim().equals(this.id.trim());
    }
}
