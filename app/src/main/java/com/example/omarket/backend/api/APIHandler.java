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


public class APIHandler{

    final static String domain = "http://192.168.1.54";
    final static String loginURL = "/api/user/login/";

    private static APIHandler apiHandler = new APIHandler();
    private APIHandler(){
    }

    public static APIHandler getInstance() {
        return apiHandler;
    }


    public static RequestQueue loginApi(Context context, Map<String, String> body){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject bodyJson = new JSONObject(body);
        final Object[] errorJson = new Object[1];
        final JSONObject[] responseJson = new JSONObject[1];
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, domain+loginURL, bodyJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    User.getCurrentLoginUser().token = (String) response.get("token");
                    User.getCurrentLoginUser().is_login = true;
                    Toast.makeText(context, "Login success full", Toast.LENGTH_SHORT).show();

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
                if(error.networkResponse.data!=null) {
                    body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                }
                try {
                    User.getCurrentLoginUser().loginErrors = new JSONObject(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(request);
        return requestQueue;
    }

//    public static JSONArray apiRequest(
//            Context context,
//            String url,
//            Map<String, String> body,
//            Map<String, String> headers,
//            Class jsonType,
//            int method
//    ) {
//        final JSONArray[] jsonArrayResponse = {null};
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        JSONObject jsonBody = null, jsonHeader;
//        JSONArray jsonArray = null;
//        if (body != null)
//            jsonBody = createJsonObejctFromMap(body);
//        if (headers != null)
//            jsonHeader = createJsonObejctFromMap(headers);
//
//
//        if (jsonType == JSONObject.class) {
//            final Object[] errors = {null};
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                    method,
//                    url,
//                    jsonBody,
//                    new Response.Listener<JSONObject>() {
//
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                response.toJSONArray(jsonArrayResponse[0]);
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
//                            errors[0] = error;
//                        }
//                    }
//            );
//            if (errors[0] != null)
//                return jsonArrayResponse[0];
//            else {
//                Map<String,String> map = new HashMap<String,String>();
//                map.put("errors",errors[0].toString());
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    try {
//                        return new JSONArray(new JSONObject(map));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        } else {
//
//        }
//
//
//        return null;
//    }
//
//    private static JSONObject createJsonObejctFromMap(Map<String, String> parameters) {
//        return new JSONObject(parameters);
//    }

//    public static void api(Context context,String url){
//        // ...
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
//            }
//        } , new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    // Add the request to the RequestQueue.
//        queue.add(jsonArrayRequest);
//    }

}
