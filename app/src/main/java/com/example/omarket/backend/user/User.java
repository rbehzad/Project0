package com.example.omarket.backend.user;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.omarket.backend.handlers.validators.UserInfoValidator;
import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.handlers.exepstions.ExceptionChecker;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class User extends CloneNotSupportedException {
    private static User currentLoginUser = new User();
    public String token;
    public boolean is_login = false;
    public JSONObject loginOrRgisterErrors;
    public String body;
    public boolean is_in_request = true;


    public String fullName;
    public String emailAddress;
    public String password;
    public String phoneNumber;
    public UserType userType;
    private Uri personPhoto;// TODO

    final UserInfoValidator validator = UserInfoValidator.getInstance();

    public Response creationResponse;

    public User() {

    }

    public User(Response creationResponse) {
        this.creationResponse = creationResponse;
    }

    public User(String emailAddress, String password, UserType userType) {
        ExceptionChecker.notNullChecker(emailAddress, userType);
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.password = password;
    }

    public User(String fullName, String emailAddress, String password, String phoneNumber, UserType userType) {
        ExceptionChecker.notNullChecker(fullName, emailAddress, password, phoneNumber, userType);
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public User(String fullName, String phoneNumber, Uri personPhoto, UserType userType) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.personPhoto = personPhoto;
    }

    public User(String fullName, Uri personPhoto, String emailAddress, UserType userType) {// google sign in constructor
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.personPhoto = personPhoto;
        this.userType = userType;
    }

    // getter
    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public Uri getPersonPhoto() {
        return personPhoto;
    }

    public static User getCurrentLoginUser() {
        if (currentLoginUser != null)
            return currentLoginUser;
        currentLoginUser = new User();
        return currentLoginUser;
    }

    @NonNull
    @NotNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
