package com.example.omarket.ui.productFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.main_fragments.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductFragment extends Fragment {
    private static final int REQUEST_CALL = 1;
    TextView title, description, cost, name, phoneNumber, email;
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton electronicBtn, fashionBtn, industrialBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        title = view.findViewById(R.id.product_fragment_textView_title);
        description = view.findViewById(R.id.product_fragment_textView_description);
        cost = view.findViewById(R.id.product_fragment_textView_cost);
        name = view.findViewById(R.id.product_fragment_textView_full_name);
        phoneNumber = view.findViewById(R.id.product_fragment_textView_phone_number);
        email = view.findViewById(R.id.product_fragment_textView_email);
        checkBox = view.findViewById(R.id.fragment_product_favoriteCheckBox);
        checkBox.setChecked(false);
        // dial up
        phoneNumber = view.findViewById(R.id.product_fragment_textView_phone_number);
        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        radioGroup = view.findViewById(R.id.product_fragment_radioGroup);
        electronicBtn = view.findViewById(R.id.product_fragment_radioBtn_electronic);
        fashionBtn = view.findViewById(R.id.product_fragment_radioBtn_fashion);
        industrialBtn = view.findViewById(R.id.pruduct_fragment_radioBtn_industrial);

        return view;
    }
    private void makePhoneCall() {
        String number = phoneNumber.getText().toString();

            if (ContextCompat.checkSelfPermission(MainActivity.mainActivity,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.mainActivity,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(MainActivity.mainActivity, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        checkBox.setChecked(false);
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        APIHandler.getAllFavoriteApi(new ServerCallback<ArrayList<String>>() {
            @Override
            public void onComplete(Result<ArrayList<String>> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success){
                    ArrayList<String> slugs = ((Result.Success<ArrayList<String>>) result).data;
                    for(Product product: Product.allProducts){
                        boolean is_in = false;
                        for(String slug: slugs){
                            if (slug.equals(product.id)){
                                is_in = true;
                                break;
                            }
                        }
                        if (is_in) {
                            checkBox.setChecked(true);
                        }
                    }
                } else if (result instanceof Result.Error){
                    Toast.makeText(MainActivity.mainActivity, "Loading favorite products failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity);
        String category = Product.selectedProduct.categorySlug;
        radioGroup.clearCheck();
        switch (category) {
            case "fashion":
                fashionBtn.setChecked(true);
                break;
            case "electronic":
                electronicBtn.setChecked(true);
                break;
            case "industrial":
                industrialBtn.setChecked(true);
                break;
            default:
                radioGroup.clearCheck();
                break;
        }
        title.setText(Product.selectedProduct.name);
        description.setText(Product.selectedProduct.description);
        cost.setText(Product.selectedProduct.price);
        final User[] user = {null};
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        body.put("email",Product.selectedProduct.userEmail);
        APIHandler.getUserInfoApi(new ServerCallback<User>() {
            @Override
            public void onComplete(Result<User> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success){
                    user[0] = ((Result.Success<User>) result).data;
                    email.setText(user[0].emailAddress);
                    phoneNumber.setText(user[0].phoneNumber);
                    name.setText(user[0].fullName);
                } else if (result instanceof Result.Error){
                    Toast.makeText(MainActivity.mainActivity, "Loading failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity, body);
    }


    public void onCheckboxClicked(View view) {
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.fragment_product_favoriteCheckBox:
                boolean checked = ((CheckBox) view).isChecked();
                HashMap<String, Object> body = new HashMap<>();
                body.put("slug", Product.selectedProduct.id);
                if (checked) {
                    APIHandler.sendRequestOrGet(new ServerCallback<String>() {
                        @Override
                        public void onComplete(Result<String> result) {
                            if (result instanceof Result.Error){
                                Toast.makeText(MainActivity.mainActivity, "Add to favorite failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, MainActivity.mainActivity, body, "SF");
                } else {
                    APIHandler.sendRequestOrGet(new ServerCallback<String>() {
                        @Override
                        public void onComplete(Result<String> result) {
                            if (result instanceof Result.Error){
                                Toast.makeText(MainActivity.mainActivity, "delete favorite failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, MainActivity.mainActivity, body, "DF");
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        boolean checked = checkBox.isChecked();
        HashMap<String, Object> body = new HashMap<>();
        final boolean[] chec = {false};
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        APIHandler.getAllFavoriteApi(new ServerCallback<ArrayList<String>>() {
            @Override
            public void onComplete(Result<ArrayList<String>> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success) {
                    ArrayList<String> slugs = ((Result.Success<ArrayList<String>>) result).data;

                    boolean is_in = false;
                    for (String slug : slugs) {
                        if (slug.equals(Product.selectedProduct.id)) {
                            is_in = true;
                            break;
                        }
                    }
                    if (is_in) {
                        chec[0] = true;
                    }
                    if (chec[0] != checked) {
                        body.put("slug", Product.selectedProduct.id);
                        if (checked) {
                            MainActivity.progressBar.setVisibility(View.VISIBLE);
                            APIHandler.sendRequestOrGet(new ServerCallback<String>() {
                                @Override
                                public void onComplete(Result<String> result) {
                                    MainActivity.progressBar.setVisibility(View.INVISIBLE);
                                    if (result instanceof Result.Error) {
                                        Toast.makeText(MainActivity.mainActivity, "Add to favorite failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, MainActivity.mainActivity, body, "SF");
                        } else {
                            MainActivity.progressBar.setVisibility(View.VISIBLE);
                            APIHandler.sendRequestOrGet(new ServerCallback<String>() {
                                @Override
                                public void onComplete(Result<String> result) {
                                    MainActivity.progressBar.setVisibility(View.INVISIBLE);
                                    if (result instanceof Result.Error) {
                                        Toast.makeText(MainActivity.mainActivity, "delete favorite failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, MainActivity.mainActivity, body, "DF");
                        }
                    }


                } else if (result instanceof Result.Error) {
                    Toast.makeText(MainActivity.mainActivity, "Loading favorite products failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity);
    }
}