package com.example.omarket.ui.fragments.home;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
import com.example.omarket.ui.fragments.Color;
import com.example.omarket.ui.fragments.SingletonFragment;


public class HomeFragment extends SingletonFragment {

    private static HomeFragment homeFragment;

    private HomeFragment(){
        homeFragment = this;
    }

    public static SingletonFragment getInstance() {
        if (homeFragment != null){
            return homeFragment;
        }
        return new HomeFragment();
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

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}