package com.example.omarket.backend.product;


import java.util.ArrayList;

public class Product {
    static ArrayList<Product> products = new ArrayList<Product>();
    public String name;
    public String price;
    public String sellerId;
    public String sellerName;
    public String sellerPhonenumber;
    public String image;
    public String info;

    public Product(String name, String price, String sellerId, String sellerName, String sellerPhonenumber, String image, String info) {
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerPhonenumber = sellerPhonenumber;
        this.image = image;
        this.info = info;
        products.add(this);
    }
}
