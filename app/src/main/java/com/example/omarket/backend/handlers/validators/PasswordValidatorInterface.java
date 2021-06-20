package com.example.omarket.backend.handlers.validators;

import com.example.omarket.R;
import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.response.ResponseErrorType;
import com.example.omarket.backend.user.User;

/**
 * singleton class.
 */
public class PasswordValidatorInterface implements ValidatorInterface {

    private static PasswordValidatorInterface passwordValidator;

    public final ResponseErrorType validatorType = ResponseErrorType.PASSWORD_VALIDATE;
    private boolean isValid;
    private String error;

    private PasswordValidatorInterface() {
        passwordValidator = this;
    }

    @Override
    public Response validate(User user) {
        return validate(user.getPassword());
    }

    public Response validate(String password) {
        return null;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String getValidateError() {
        return error;
    }

    @Override
    public ResponseErrorType getValidatorType() {
        return validatorType;
    }

    public static ValidatorInterface getInstance() {
        return null;
    }


}
