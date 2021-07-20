package com.example.omarket.backend.api;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.omarket.backend.user.User;
import com.example.omarket.backend.user.UserType;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class APIHandler implements Response.ErrorListener {

    final static String domain = "http://192.168.1.105";
    final static String loginURL = "/api/user/login/";
    final static String registerURL = "/api/user/register/";
    final static String userInfoURL = "/api/user/info/";
    final static String userInfoUpdateURL = "/api/user/update/";
    final static String addProductURL = "/api/product/create/";
    final static String updateProductURL = "/api/product/update/";

    public static APIHandler apiHandler = new APIHandler();

    public static void loginOrRegisterApi(Context context, Map<String, String> body, String requestType) {

        String requestURL = (requestType.equals("login") ? loginURL : registerURL);
        String tag = (requestType.equals("login") ? "login" : "register");

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject bodyJson = new JSONObject(body);
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
                String body = null;
                //get status code here
                String statusCode;
                if (error.networkResponse != null) {
                    statusCode = String.valueOf(error.networkResponse.statusCode);
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
                } else Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag(tag);
        requestQueue.add(request);
    }

    public static void getUserInfoApi(Context context, Map<String, Object> body, String C_N) {
        // C : current user , N : not current user
        JSONObject jsonBody = new JSONObject(body);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, domain + userInfoURL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                User user = null;
                if (C_N.equals("C"))
                    user = User.getCurrentLoginUser();
                else
                    user = new User();
                try {
                    user.emailAddress = (String) response.get("email");
                    user.fullName = (String) response.get("first_name") + " " + (String) response.get("last_name");
                    boolean is_admin = response.getBoolean("is_superuser");
                    user.userType = (is_admin ? UserType.SUPER_ADMIN : UserType.USER);
                    // get image:
                    boolean is_null = response.isNull("base64_image");
                    if (!is_null) {
                        String encodedImage = (String) response.get("base64_image");
                        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                        user.personPhotoBitmap = BitmapFactory.decodeByteArray(
                                decodedString,
                                0,
                                decodedString.length
                        );
                    }

                    String phone_number = null;
                    if (!response.isNull("phone_number")) {
                        phone_number = (String) response.get("phone_number");
                    }
                    user.phoneNumber = phone_number;
                    user.isInProgress = false;
                    if (C_N.equals("C"))
                        User.currentLoginUser = user;
                    else
                        User.allUser.add(user);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, apiHandler) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token " + User.currentLoginUser.token);
                return params;
            }
        };

        request.setTag("UserInfo");
        requestQueue.add(request);
    }

    public static void updateUserAddProductUpdateProductApi(Context context, Map<String, Object> body, String UU_AP_UP) {
        String requestURL = null;
        switch (UU_AP_UP) {
            case "UU":
                requestURL = userInfoUpdateURL;
                break;
            case "AP":
                requestURL = addProductURL;
                break;
            case "UP":
                requestURL = updateProductURL;
                break;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject bodyJson = new JSONObject(body);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, domain + requestURL, bodyJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(context, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, apiHandler) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token " + User.currentLoginUser.token);
                return params;
            }
        };
        requestQueue.add(request);

    }

    //todo get product , category
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onErrorResponse(VolleyError error) {
        String body = null;
        //get status code here
        String statusCode;
        if (error.networkResponse != null) {
            statusCode = String.valueOf(error.networkResponse.statusCode);
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
    }
}
