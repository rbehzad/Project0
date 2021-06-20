package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.response.ResponseErrorType;
import com.example.omarket.backend.user.User;


public interface ValidatorInterface {
    // if value that we want was null return unsuccessful response.
    Response validate(User user);

    boolean isValid();

    String getValidateError();

    ResponseErrorType getValidatorType();

    static ValidatorInterface getInstance() {
        return null;
    }


}
