package com.example.omarket.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.omarket.R;
import com.example.omarket.ui.NavigationFragment;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends NavigationFragment implements View.OnClickListener {

    Button loginButton;
    TextView register;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        loginButton = view.findViewById(R.id.login_login_button);
        loginButton.setOnClickListener(this);
        register = view.findViewById(R.id.login_text_view_register);
        register.setOnClickListener(this);

        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login_button:
                navigateFromViewTo(v,R.id.action_loginFragment_to_mainActivity);
                break;
            case R.id.login_text_view_register:
                navigateFromViewTo(v,R.id.action_loginFragment_to_registerFragment);
                break;
        }
    }

}