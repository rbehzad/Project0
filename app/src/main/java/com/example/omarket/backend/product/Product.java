package com.example.omarket.backend.product;


import com.example.omarket.backend.user.User;

import java.util.ArrayList;

public class Product {
    public String name;
    public long price;
    public User user;
    public String image;
    public String description;

    public Product(){

    }

    public Product(String name, long price,  String image, String description, User user) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.user = user;
        this.description = description;
    }
}
