package com.example.omarket.backend.data.data.repository;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}
