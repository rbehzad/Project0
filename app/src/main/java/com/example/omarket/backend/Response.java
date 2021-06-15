package com.example.omarket.backend;

public class Response {
    private String[] errors;
    private boolean success;

    // when got errors
    private Response(String ...errors){
        this.errors = errors;
        success = false;
    }

    // when no errors return.
    private Response(){
        success = true;
    }

    static Response setErrors(String ...errors){
        return new Response(errors);
    }

    static Response setResponse(){
        return new Response();
    }

    public String[] getErrors() {
        return errors;
    }

    public boolean isSuccess() {
        return success;
    }

}
