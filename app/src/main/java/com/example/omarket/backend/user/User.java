package com.example.omarket.backend.user;

import com.example.omarket.backend.Response;
import com.example.omarket.backend.handlers.exepstions.ExceptionChecker;
import com.example.omarket.backend.handlers.validators.EmailValidator;
import com.example.omarket.backend.handlers.validators.PasswordValidator;
import com.example.omarket.backend.handlers.validators.PhoneNumberValidator;
import com.example.omarket.backend.handlers.validators.Validator;

public class User {

    private static User currentLoginUser;

    private UserInformation userInformation;

    private Validator[] validators;
    public Response creationResponse;

    public User(Response creationResponse) {
        this.creationResponse = creationResponse;
    }

    public User(String emailAddress, String password, boolean isAdmin, boolean isSuperAdmin) {
        setValidators();
        ExceptionChecker.notNullChecker(emailAddress, isAdmin, isSuperAdmin);
        this.userInformation.emailAddress = emailAddress;
        this.userInformation.isAdmin = isAdmin;
        this.userInformation.isSuperAdmin = isSuperAdmin;
        this.userInformation.password = password;
    }

    public User(String fullName, String emailAddress, String password, String phoneNumber,
                boolean isAdmin, boolean isSuperAdmin) {
        ExceptionChecker.notNullChecker(fullName, emailAddress, password, phoneNumber, isAdmin, isSuperAdmin);
        setValidators();
        this.userInformation.fullName = fullName;
        this.userInformation.emailAddress = emailAddress;
        this.userInformation.password = password;
        this.userInformation.phoneNumber = phoneNumber;
        this.userInformation.isAdmin = isAdmin;
        this.userInformation.isSuperAdmin = isSuperAdmin;
    }

    public String getFullName() {
        return userInformation.fullName;
    }

    public String getEmailAddress() {
        return userInformation.emailAddress;
    }

    public String getPassword() {
        return userInformation.password;
    }

    public String getPhoneNumber() {
        return userInformation.phoneNumber;
    }

    public boolean isSuperAdmin() {
        return userInformation.isSuperAdmin;
    }

    public boolean isAdmin() {
        return userInformation.isAdmin;
    }

    public static User getCurrentLoginUser() {
        return currentLoginUser;
    }

    private void setValidators() {
        validators = new Validator[]{
                EmailValidator.getInstance(),
                PasswordValidator.getInstance(),
                PhoneNumberValidator.getInstance()
        };
    }

    public User createNewUser() {
        creationResponse = Response.successful();
        for (Validator validator :
                validators) {
            validator.validate(userInformation);
            if (!validator.isValid()) {
                creationResponse.success = false;
                creationResponse.errors.add(validator.getValidateError());
            }
        }
        if (creationResponse.success)
            return this;
        else
            return new User(creationResponse);
    }


}
