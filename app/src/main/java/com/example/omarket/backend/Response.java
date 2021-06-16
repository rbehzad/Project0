package com.example.omarket.backend;

import java.util.ArrayList;

public class Response {
    public ArrayList<String> errors;
    public boolean success;


    public static Response setErrors(String... errors) {
        Response response = new Response();
        for (String err :
                errors) {
            response.errors.add(err);
        }
        response.success = false;
        return response;
    }


    public static Response seccessful() {
        Response response = new Response();
        response.success = true;
        return response;
    }

}
