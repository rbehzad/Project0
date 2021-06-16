package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.Response;
import com.example.omarket.backend.user.UserInformation;


public interface Validator {
    // if value that we want was null return unsuccessful response.
    Response validate(UserInformation userInformation);

    boolean isValid();

    String getValidateError();

    static Validator getInstance() {
        return null;
    }
}
