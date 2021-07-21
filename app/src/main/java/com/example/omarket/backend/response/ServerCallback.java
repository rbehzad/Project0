package com.example.omarket.backend.response;

public interface ServerCallback<T> {
    abstract void onComplete(Result<T> result);
}
