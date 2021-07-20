package com.example.omarket.backend.api;

import com.example.omarket.backend.product.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Adapter {

    public static Product productApiAdapter(JSONObject json){
        Product p = new Product();
        try{
            p.name = (String) json.get("title");
            p.description = (String) json.get("description");
            p.price = String.valueOf(json.getLong("cost"));
            p.id = json.getString("slug");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
