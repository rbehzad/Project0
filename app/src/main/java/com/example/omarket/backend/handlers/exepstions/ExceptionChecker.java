package com.example.omarket.backend.handlers.exepstions;

public class ExceptionChecker {

    public static boolean notNullChecker(Object... objects) throws NullPointerException {
        for (Object obj :
                objects) {
            if (obj == null) {
                throw new NullPointerException();
            }
        }
        return true;
    }
}
