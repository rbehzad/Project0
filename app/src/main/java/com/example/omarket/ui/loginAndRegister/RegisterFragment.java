package com.example.omarket.ui.loginAndRegister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.omarket.R;
import com.example.omarket.backend.data.data.repository.Repository;
import com.example.omarket.backend.data.data.repository.RepositoryCallback;
import com.example.omarket.backend.data.data.repository.Result;
import com.example.omarket.backend.user.User;

import org.jetbrains.annotations.NotNull;

public class RegisterFragment extends Fragment {
    private TextView loginTextView;
    private TextView registerButton;
    EditText fullName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText password;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        loginTextView = view.findViewById(R.id.register_text_view_already_registered);
        registerButton = view.findViewById(R.id.register_button_register);
        // switch activity_register to login
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
        // switch activity_register to login
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        fullName =  (EditText) view.findViewById(R.id.register_edit_text_name);
        phoneNumber =  (EditText) view.findViewById(R.id.register_edit_text_phone_number);
        emailAddress =  (EditText) view.findViewById(R.id.register_edit_text_email_address);
        password =  (EditText) view.findViewById(R.id.register_edit_text_password);
        // insert a new user in client database
        RepositoryCallback<Void> callback = new RepositoryCallback<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                if(result instanceof Result.Success) {
                    Toast.makeText(getContext().getApplicationContext(),  "success", Toast.LENGTH_LONG);
                }
            }
        };
        Repository.getInstance(getContext().getApplicationContext()).insertUser(
                fullName.getText().toString(), emailAddress.getText().toString(), password.getText().toString(), phoneNumber.getText().toString(), "USER",  callback);//
        return view;
    }
}
