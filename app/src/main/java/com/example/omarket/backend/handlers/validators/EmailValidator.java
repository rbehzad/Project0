package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.Response;
import com.example.omarket.backend.user.UserInformation;

/**
 * singleton class.
 * if email dose exist.
 * if email hase invalid char or something.
 */
public class EmailValidator implements Validator {

    private static EmailValidator emailValidator;

    private Validator nextValidator;
    private boolean isValid;

    private EmailValidator() {
        emailValidator = this;
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
        if (emailValidator != null) {
            return emailValidator;
        }
        return new EmailValidator();
    }


}
