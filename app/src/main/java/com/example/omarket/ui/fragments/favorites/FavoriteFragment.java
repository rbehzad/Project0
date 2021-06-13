package com.example.omarket.ui.fragments.favorites;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
import com.example.omarket.ui.fragments.Color;
import com.example.omarket.ui.fragments.SingletonFragment;


public class FavoriteFragment extends SingletonFragment {

    private static FavoriteFragment favoriteFragment;

    private FavoriteFragment(){
        favoriteFragment = this;
    }

    public static SingletonFragment getInstance() {
        if (favoriteFragment != null){
            return favoriteFragment;
        }
        return new FavoriteFragment();
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

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

}