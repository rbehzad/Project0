package com.example.omarket.backend.handlers.validators;

import junit.framework.TestCase;

import java.util.Scanner;

public class EmailValidatorTest extends TestCase {

    public void testValidate() {
        EmailValidator emailValidator = EmailValidator.getInstance();
        assertEquals(emailValidator.validate("").success,true);
    }
}