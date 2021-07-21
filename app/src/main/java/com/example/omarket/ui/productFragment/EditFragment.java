package com.example.omarket.ui.productFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.main_fragments.MainActivity;

import java.util.HashMap;

public class EditFragment extends NavigationFragment implements View.OnClickListener {
    EditText title;
    EditText description;
    EditText cost;
    TextView fullName, phoneNumber, email;
    Button saveBtn, deleteBtn;

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
            email = view.findViewById(R.id.product_fragment_textView_email);
            saveBtn = view.findViewById(R.id.edit_fragment_btn_save);
            saveBtn.setOnClickListener(this);
            deleteBtn = view.findViewById(R.id.edit_fragment_btn_delete);
            deleteBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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
                    fullName.setText(user[0].fullName);
                } else if (result instanceof Result.Error){
                    Toast.makeText(getActivity(), "Loading failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, getActivity(), body);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_fragment_btn_save:
                updateProduct();
                break;
            case R.id.edit_fragment_btn_delete:
                deleteProduct();
                break;
        }
    }

    public void deleteProduct(){
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        body.put("slug", Product.selectedProduct.id);
        APIHandler.sendRequestOrGet(new ServerCallback<String>() {
            @Override
            public void onComplete(Result<String> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success){
                    Toast.makeText(getActivity(), "Deleting product successful", Toast.LENGTH_SHORT).show();
                    navigateFromViewTo(getView(), R.id.action_fragment_editId_to_homeFragment);
                }
                else if (result instanceof Result.Error){
                    Toast.makeText(getActivity(), "Deleting filed", Toast.LENGTH_SHORT).show();
                }
            }
        }, getActivity(), body, "DP");
    }

    public void updateProduct(){
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
                if (result instanceof Result.Success){
                    Toast.makeText(getActivity(), "Update product successful", Toast.LENGTH_SHORT).show();
                    navigateFromViewTo(getView(), R.id.action_fragment_editId_to_homeFragment);
                }
                else if (result instanceof Result.Error){
                    Toast.makeText(getActivity(), "Update filed", Toast.LENGTH_SHORT).show();
                }
            }
        }, getActivity(), body, "UP");
    }

    @Override
    public void onPause() {
        super.onPause();
        Product.selectedProduct = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Product.selectedProduct = null;
    }
}