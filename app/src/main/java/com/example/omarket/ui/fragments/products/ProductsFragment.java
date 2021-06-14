package com.example.omarket.ui.fragments.products;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
import com.example.omarket.ui.fragments.Color;
import com.example.omarket.ui.fragments.SingletonFragment;

public class ProductsFragment extends SingletonFragment {

    public static ProductsFragment productsFragment;

    private ProductsFragment(){
        productsFragment = this;
    }

    public static ProductsFragment getInstance() {
        if (productsFragment != null){
            return productsFragment;
        }
        return new ProductsFragment();
    }

    @Override
    public void changeColorTo(Color color) {
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_products, container, false);
    }
}