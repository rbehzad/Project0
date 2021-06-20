package com.example.omarket.backend.user;

import android.net.Uri;

import com.example.omarket.backend.handlers.validators.UserInfoValidator;
import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.handlers.exepstions.ExceptionChecker;

public class User {
    private static User currentLoginUser;


    private String fullName;
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private UserType userType;
    private Uri personPhoto;// TODO

    final UserInfoValidator validator = UserInfoValidator.getInstance();

    public Response creationResponse;

    User(Response creationResponse) {
        this.creationResponse = creationResponse;
    }

    User(String emailAddress, String password, UserType userType) {
        ExceptionChecker.notNullChecker(emailAddress, userType);
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.password = password;
    }

    User(String fullName, String emailAddress, String password, String phoneNumber, UserType userType) {
        ExceptionChecker.notNullChecker(fullName, emailAddress, password, phoneNumber, userType);
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    User(String fullName, String phoneNumber, Uri personPhoto, UserType userType) {
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


    public User createNewUser() {
        creationResponse = validator.validate(this);
        if (creationResponse.success)
            return this;
        else
            return new User(creationResponse);
    }
}
