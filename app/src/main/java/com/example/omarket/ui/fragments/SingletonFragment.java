package com.example.omarket.ui.fragments;

import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public abstract class SingletonFragment extends Fragment {

    protected BottomNavigationItemView bottomNavigationItem;
    protected Drawable drawable;
    protected boolean isOnClicked = false;

    public static SingletonFragment getInstance(){
        return null;
    }

    abstract public void changeColorTo(Color color);

}
