package com.example.omarket.backend.api;

public enum APIRequest {
    Domain(
            "127.0.0.1:8000"
    ),
    Register(
            "/api/user/register/"
    ),
    Login(
            "/api/user/login/"
    ),
    UpdateUser(
            "/api/user/update/"
    ),
    CategoryList(
            "/api/product/category/all/"
    ),
    ProductsOfCategory(
            "/api/product/category/"
    ),
    AddProduct(
            "/api/product/product/create/"
    ),
    UpdateProduct(
            "/api/product/product/"
    ),
    DeleteProduct(
            "/api/product/product/"
    );

    public String URL;

    APIRequest(String URL) {
        this.URL = URL;
    }
}
