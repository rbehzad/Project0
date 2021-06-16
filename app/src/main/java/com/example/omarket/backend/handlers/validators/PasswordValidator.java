package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.Response;
import com.example.omarket.backend.user.UserInformation;

/**
 * singleton class.
 */
public class PasswordValidator implements Validator {

    private static PasswordValidator passwordValidator;

    private PasswordValidator() {
        passwordValidator = this;
    }

    @Override
    public Response validate(UserInformation userInformation) {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getValidateError() {
        return null;
    }

    public static Validator getInstance() {
        return null;
    }


}
