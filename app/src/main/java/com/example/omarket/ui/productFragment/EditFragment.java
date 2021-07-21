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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.main_fragments.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class EditFragment extends NavigationFragment implements View.OnClickListener {
    private static final int REQUEST_CALL = 1;
    EditText title;
    EditText description;
    EditText cost;
    TextView fullName, phoneNumber, email;
    Button saveBtn, deleteBtn;
    CheckBox checkBox;

    public EditFragment editFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        editFragment = this;
        title = view.findViewById(R.id.edit_fragment_editText_title);
        description = view.findViewById(R.id.edit_fragment_editText_description);
        cost = view.findViewById(R.id.edit_fragment_editText_cost);
        fullName = view.findViewById(R.id.edit_fragment_textView_full_name);
        phoneNumber = view.findViewById(R.id.product_fragment_textView_phone_number);
        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        email = view.findViewById(R.id.product_fragment_textView_email);
        saveBtn = view.findViewById(R.id.edit_fragment_btn_save);
        saveBtn.setOnClickListener(this);
        deleteBtn = view.findViewById(R.id.edit_fragment_btn_delete);
        deleteBtn.setOnClickListener(this);
        checkBox = view.findViewById(R.id.fragment_edit_product_editcheckBox);
        checkBox.setOnClickListener(this);
        checkBox.setChecked(false);


        return view;
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
                        checkBox.setChecked(true);
                    }

                } else if (result instanceof Result.Error) {
                    Toast.makeText(MainActivity.mainActivity, "Loading favorite products failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity);
        title.setText(Product.selectedProduct.name);
        description.setText(Product.selectedProduct.description);
        cost.setText(Product.selectedProduct.price);
        final User[] user = {null};
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        body.put("email", Product.selectedProduct.userEmail);
        APIHandler.getUserInfoApi(new ServerCallback<User>() {
            @Override
            public void onComplete(Result<User> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success) {
                    user[0] = ((Result.Success<User>) result).data;
                    email.setText(user[0].emailAddress);
                    phoneNumber.setText(user[0].phoneNumber);
                    fullName.setText(user[0].fullName);
                } else if (result instanceof Result.Error) {
                    Toast.makeText(MainActivity.mainActivity, "Loading failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity, body);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_fragment_btn_save:
                updateProduct();
                break;
            case R.id.edit_fragment_btn_delete:
                deleteProduct();
                break;
//            case R.id.fragment_product_favoriteCheckBox:
//                onCheckboxClicked(v);
//                break;
        }
    }

    public void deleteProduct() {
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        body.put("slug", Product.selectedProduct.id);
        APIHandler.sendRequestOrGet(new ServerCallback<String>() {
            @Override
            public void onComplete(Result<String> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success) {
                    Toast.makeText(MainActivity.mainActivity, "Deleting product successful", Toast.LENGTH_SHORT).show();
                    navigateFromViewTo(getView(), R.id.action_fragment_editId_to_homeFragment);
                } else if (result instanceof Result.Error) {
                    Toast.makeText(MainActivity.mainActivity, "Deleting filed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity, body, "DP");
    }

    public void updateProduct() {
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        body.put("slug", Product.selectedProduct.id);
        body.put("title", title.getText().toString());
        body.put("description", title.getText().toString());
        body.put("cost", Long.parseLong(cost.getText().toString()));
        APIHandler.sendRequestOrGet(new ServerCallback<String>() {
            @Override
            public void onComplete(Result<String> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success) {
                    Toast.makeText(MainActivity.mainActivity, "Update product successful", Toast.LENGTH_SHORT).show();
                    navigateFromViewTo(getView(), R.id.action_fragment_editId_to_homeFragment);
                } else if (result instanceof Result.Error) {
                    Toast.makeText(MainActivity.mainActivity, "Update filed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity, body, "UP");
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
    public void onDestroy() {
        super.onDestroy();
    }
}