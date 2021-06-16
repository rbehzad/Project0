package com.example.omarket.backend.handlers.validators;

import com.example.omarket.backend.Response;
import com.example.omarket.backend.user.UserInformation;

/**
 * singleton class.
 */
public class PhoneNumberValidator implements Validator {

    private static PhoneNumberValidator phoneNumberValidator;

    private PhoneNumberValidator() {
        phoneNumberValidator = this;
    }

    public static Validator getInstance() {
        return null;
    }

}
