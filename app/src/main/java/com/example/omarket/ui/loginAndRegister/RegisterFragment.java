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
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
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
        changeVisibilityTo(progressBar, View.VISIBLE);
        if (User.currentLoginUser.is_login){
            HashMap<String, Object> logiBody = new HashMap<>();
            logiBody.put("email", User.currentLoginUser.emailAddress);
            APIHandler.getUserInfoApi(new ServerCallback<User>() {
                @Override
                public void onComplete(Result<User> result) {
                    changeVisibilityTo(progressBar, View.INVISIBLE);
                    if (result instanceof Result.Success) {
                        User u = (User) ((Result.Success) result).data;
                        u.token = User.currentLoginUser.token;
                        u.is_login = User.currentLoginUser.is_login;
                        User.currentLoginUser = u;
                        navigateFromViewTo(getView(),R.id.action_registerFragment_to_loginFragment);
                    } else if (result instanceof Result.Error) {
                        Toast.makeText(getActivity(), "register field, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }, getActivity(), logiBody);
        } else {
            APIHandler.loginOrRegisterApi(new ServerCallback<User>() {
                @Override
                public void onComplete(Result<User> result) {
                    changeVisibilityTo(progressBar, View.INVISIBLE);
                    if (result instanceof Result.Success) {
                        User user = (User) ((Result.Success) result).data;
                        if (user.is_login) {
                            User.currentLoginUser.token = user.token;
                            User.currentLoginUser.is_login = user.is_login;
                            changeVisibilityTo(progressBar, View.VISIBLE);
                            HashMap<String, Object> body = new HashMap<>();
                            body.put("email", User.currentLoginUser.emailAddress);
                            APIHandler.getUserInfoApi(new ServerCallback<User>() {
                                @Override
                                public void onComplete(Result<User> result) {
                                    changeVisibilityTo(progressBar, View.INVISIBLE);
                                    if (result instanceof Result.Success) {
                                        User u = (User) ((Result.Success) result).data;
                                        u.token = User.currentLoginUser.token;
                                        u.is_login = User.currentLoginUser.is_login;
                                        User.currentLoginUser = u;
                                        navigateFromViewTo(getView(),R.id.action_registerFragment_to_mainActivity);
                                    } else if (result instanceof Result.Error) {
                                        Toast.makeText(getActivity(), "register field, try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, getActivity(), body);
                        }
                    } else if (result instanceof Result.Error) {
                        User user = (User) ((Result.Error) result).data;
                        if (user.loginOrRgisterErrors != null) {
                            String email = "", password = "";
                            try {
                                email = user.loginOrRgisterErrors.get("email").toString();
                                if (emailText.getText() == null || emailText.getText().toString().trim().equals("")) {
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
                                password = user.loginOrRgisterErrors.get("password").toString();
                                passwordText.setText("");
                                passwordText.setHint(password);
                                viewFailed(passwordText);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (email.trim().equals("") && password.trim().equals("") && user.loginOrRgisterErrors != null) {
                                Toast.makeText(getActivity(), user.loginOrRgisterErrors.toString(), Toast.LENGTH_SHORT).show();
                            }
                            User.getCurrentLoginUser().loginOrRgisterErrors = null;
                        }
                        Toast.makeText(getActivity(), "Register field, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }, getActivity(), body, "register");
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
