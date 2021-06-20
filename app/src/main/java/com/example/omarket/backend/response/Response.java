package com.example.omarket.backend.response;

import androidx.core.util.Pair;

import java.util.ArrayList;

public class Response {
    public ArrayList<Pair<ResponseErrorType, String>> errors;
    public boolean success;

    public static Response setErrors(Pair<ResponseErrorType, String>... errors) {
        Response response = new Response();
        for (Pair<ResponseErrorType, String> err :
                errors) {
            response.errors.add(err);
        }
        response.success = false;
        return response;
    }


    public static Response successful() {
        Response response = new Response();
        response.success = true;
        return response;
    }

}
