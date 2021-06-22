package com.example.omarket.backend.user;

import android.net.Uri;

import com.example.omarket.backend.handlers.validators.UserInfoValidator;
import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.handlers.exepstions.ExceptionChecker;

public class User {
    private static User currentLoginUser;


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
        return currentLoginUser;
    }

}
