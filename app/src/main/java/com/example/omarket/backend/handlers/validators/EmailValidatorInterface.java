package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.response.ResponseErrorType;
import com.example.omarket.backend.user.User;

/**
 * singleton class.
 * if email dose exist.
 * if email hase invalid char or something.
 */
public class EmailValidatorInterface implements ValidatorInterface {

    private static EmailValidatorInterface emailValidator;

    public final ResponseErrorType validatorType = ResponseErrorType.EMAIL_VALIDATE;
    private boolean isValid;
    private String error;

    private EmailValidatorInterface() {
        emailValidator = this;
    }

    public static ValidatorInterface getInstance() {
        if (emailValidator != null) {
            return emailValidator;
        }
        return new EmailValidatorInterface();
    }

    @Override
    public Response validate(User user) {
        return validate(user.getEmailAddress());
    }

    public Response validate(String email) {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getValidateError() {
        return error;
    }

    @Override
    public ResponseErrorType getValidatorType() {
        return validatorType;
    }


}
