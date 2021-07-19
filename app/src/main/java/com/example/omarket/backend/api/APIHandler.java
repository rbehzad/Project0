package com.example.omarket.backend.api;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.omarket.backend.user.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;


public class APIHandler {

    final static String domain = "http://192.168.1.54";
    final static String loginURL = "/api/user/login/";
    final static String registerURL = "/api/user/register/";

    private static final APIHandler apiHandler = new APIHandler();


    private APIHandler() {
    }

    public static APIHandler getInstance() {
        return apiHandler;
    }


    public static RequestQueue loginOrRegisterApi(Context context, Map<String, String> body, String requestType) {

        String requestURL = (requestType.equals("login") ? loginURL : registerURL);
        String tag = (requestType.equals("login") ? "login" : "register");

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject bodyJson = new JSONObject(body);
        final Object[] errorJson = new Object[1];
        final JSONObject[] responseJson = new JSONObject[1];
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, domain + requestURL, bodyJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    User.getCurrentLoginUser().token = (String) response.get("token");
                    User.getCurrentLoginUser().is_login = true;
                    Toast.makeText(context, response.get("response").toString(), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, error.networkResponse.statusCode + " "
//                        + new String(error.networkResponse.data, StandardCharsets.UTF_8), Toast.LENGTH_SHORT).show();
                String body = null;
                //get status code here
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                if (error.networkResponse.data != null) {
                    body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                }
                try {
                    if (body != null)
                        User.getCurrentLoginUser().loginOrRgisterErrors = new JSONObject(body);
                    else
                        User.getCurrentLoginUser().loginOrRgisterErrors = new JSONObject("{\"response\":\"Request failed\"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        request.setTag(tag);
        requestQueue.add(request);
        return requestQueue;
    }




}
