package com.example.omarket.backend.data.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.omarket.backend.data.entities.Product;

import java.util.List;
import java.util.Scanner;

@Dao
public interface ProductDao {

    @Query("INSERT INTO Product VALUES(:id, :name, :info, :imagePath, :sellerName, :sellerId)")
    void insert(int id, String name, String info, String imagePath, String sellerName, String sellerId);

    // get product info by searching its name
    @Query("SELECT * FROM Product WHERE name IN (:productName) ")
    List<Product> loadAllByIds(int[] productName);

    @Insert
    void insertAll(Product...products);

    @Delete
    void delete(Product product);
}
