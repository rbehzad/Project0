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

    public static Validator getInstance() {
        return null;
    }


}
