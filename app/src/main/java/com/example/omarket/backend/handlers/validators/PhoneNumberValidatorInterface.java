package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.response.ResponseErrorType;
import com.example.omarket.backend.user.User;

/**
 * singleton class.
 */
public class PhoneNumberValidatorInterface implements ValidatorInterface {

    private static PhoneNumberValidatorInterface phoneNumberValidator;

    public final ResponseErrorType validatorType = ResponseErrorType.PHONE_NUMBER_VALIDATE;
    private boolean isValid;
    private String error;

    private PhoneNumberValidatorInterface() {
        phoneNumberValidator = this;
    }

    @Override
    public Response validate(User user) {
        return validate(user.getPhoneNumber());
    }

    public Response validate(String phoneNumber) {
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
