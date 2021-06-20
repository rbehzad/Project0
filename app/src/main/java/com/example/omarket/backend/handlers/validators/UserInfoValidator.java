package com.example.omarket.backend.handlers.validators;

import androidx.core.util.Pair;

import com.example.omarket.backend.response.Response;
import com.example.omarket.backend.response.ResponseErrorType;
import com.example.omarket.backend.user.User;

/**
 * singleton class
 */
public class UserInfoValidator implements ValidatorInterface {

    private static UserInfoValidator userInfoValidator;

    public final ResponseErrorType validatorType = ResponseErrorType.VALIDATOR;
    private ValidatorInterface[] validators;
    private Response response;
    private boolean isValid;

    private UserInfoValidator() {
        setValidators();
        userInfoValidator = this;
    }

    public static UserInfoValidator getInstance() {
        if (userInfoValidator != null)
            return userInfoValidator;
        return new UserInfoValidator();
    }

    private void setValidators() {
        this.validators = new ValidatorInterface[]{
                EmailValidatorInterface.getInstance(),
                PasswordValidatorInterface.getInstance(),
                PhoneNumberValidatorInterface.getInstance()
        };
    }

    @Override
    public Response validate(User user) {
        response = Response.successful();
        for (ValidatorInterface validator :
                validators) {
            validator.validate(user);
            if (!validator.isValid()) {
                response.success = false;
                response.errors
                        .add(new Pair<>(validator.getValidatorType(), validator.getValidateError()));
            }
        }
        this.isValid = response.success;
        return response;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String getValidateError() {
        return response.errors.get(0).second;
    }

    @Override
    public ResponseErrorType getValidatorType() {
        return validatorType;
    }

}
