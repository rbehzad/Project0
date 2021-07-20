package com.example.omarket.ui.loginAndRegister;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.main_fragments.Color;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

public class RegisterFragment extends NavigationFragment implements View.OnTouchListener, View.OnClickListener {
    private TextView loginTextView;
    private TextView registerButton;
    EditText phoneNumber;
    EditText emailText;
    EditText passwordText;
    EditText firstName, lastName;
    ProgressBar progressBar;

    private Animation shakeAnimation;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        loginTextView = view.findViewById(R.id.register_text_view_login);
        loginTextView.setOnClickListener(this);
        registerButton = view.findViewById(R.id.register_button_register);
        registerButton.setOnClickListener(this);

        firstName = (EditText) view.findViewById(R.id.register_edit_text_first_name);
        lastName = (EditText) view.findViewById(R.id.register_edit_text_last_name);
        phoneNumber = (EditText) view.findViewById(R.id.register_edit_text_phone_number);
        emailText = (EditText) view.findViewById(R.id.register_edit_text_email_address);
        passwordText = (EditText) view.findViewById(R.id.register_edit_text_password);
        passwordText.setOnTouchListener(this);
        progressBar = (ProgressBar) view.findViewById(R.id.register_fragmetn_progressBar);

        AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
//        // insert a new user in client database
//        RepositoryCallback<Void> callback = new RepositoryCallback<Void>() {
//            @Override
//            public void onComplete(Result<Void> result) {
//                if(result instanceof Result.Success) {
//                    Toast.makeText(getContext().getApplicationContext(),  "success", Toast.LENGTH_LONG);
//                }
//            }
//        };
//        Repository.getInstance(getContext().getApplicationContext()).insertUser(
//                fullName.getText().toString(), emailAddress.getText().toString(), password.getText().toString(), phoneNumber.getText().toString(), "USER",  callback);//
        return view;
    }


    @SuppressLint({"ResourceType", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        setDefaultConfig();
        switch (v.getId()) {
            case R.id.register_button_register:
                register(v);
                break;
            case R.id.register_text_view_login:
                navigateFromViewTo(v, R.id.action_registerFragment_to_loginFragment);
                break;

        }
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId", "ClickableViewAccessibility"})
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setDefaultConfig();
        switch (v.getId()) {
            case R.id.register_edit_text_password:
                if (emailText.getText() == null || "".equals(emailText.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "Email address can't be empty!", Toast.LENGTH_SHORT).show();
                    return false;
                }
        }
        return false;
    }


    public void setDefaultConfig() {
        emailText.setHint("Email Address");
        passwordText.setHint("Password");
        Color.changeViewColor(emailText, R.color.black);
        Color.changeViewColor(passwordText, R.color.black);
        Color.changeHintViewColor(emailText, R.color.gray);
        Color.changeHintViewColor(passwordText, R.color.gray);
    }

    private void register(View v) {
        User.currentLoginUser.isInProgress = false;
        HashMap<String, String> body = new HashMap<>();
        body.put("email", emailText.getText().toString());
        body.put("password", passwordText.getText().toString());
        body.put("password2", passwordText.getText().toString());
        body.put("first_name", firstName.getText().toString());
        body.put("last_name", lastName.getText().toString());
        body.put("phone_number", phoneNumber.getText().toString());
        Thread thread = new Thread() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                APIHandler.loginOrRegisterApi(getActivity(), body, "register");
                User user;
                do {
                    user = User.getCurrentLoginUser();
                } while (!user.is_login && user.loginOrRgisterErrors == null);
                changeVisibilityTo(progressBar, View.INVISIBLE);

            }
        };
        changeVisibilityTo(progressBar, View.VISIBLE);
        thread.start();
        changeVisibilityTo(progressBar, View.INVISIBLE);
        User user = User.getCurrentLoginUser();
        if (user.is_login) {
            changeVisibilityTo(progressBar, View.VISIBLE);
            APIHandler.getUserInfoApi(getActivity());
            changeVisibilityTo(progressBar, View.VISIBLE);
            thread.start();
            navigateFromViewTo(getView(), R.id.action_registerFragment_to_mainActivity);
            return;
        }

        if (user.loginOrRgisterErrors != null) {


            try {
                String email = user.loginOrRgisterErrors.get("email").toString();
                if (emailText.getText().toString().trim().equals("") || emailText.getText() == null) {
                    emailText.setText("");
                    emailText.setHint(email);
                    viewFailed(emailText);
                } else {
                    Toast.makeText(getActivity(), email, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String password = user.loginOrRgisterErrors.get("password").toString();
                passwordText.setText("");
                passwordText.setHint(password);
                viewFailed(passwordText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (User.getCurrentLoginUser().loginOrRgisterErrors != null) {
                Toast t = new Toast(getActivity());
                t.setText(user.loginOrRgisterErrors.toString());
                t.setDuration(Toast.LENGTH_LONG);
                final Toast tt = t;
                tt.show();

            }
        }


    }

    public void changeVisibilityTo(View v, int visibility) {
        v.setVisibility(visibility);
    }

    private void viewFailed(View v) {
        Color.changeViewColor(v, R.color.red);
        Color.changeHintViewColor(v, R.color.red);
        v.startAnimation(shakeAnimation);
    }

    @SuppressLint("SetTextI18n")
    private void loginFailedAction() {
        passwordText.setText("");
        Color.changeViewColor(emailText, R.color.red);
        passwordText.startAnimation(shakeAnimation);
        emailText.startAnimation(shakeAnimation);

    }
}
