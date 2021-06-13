package com.example.omarket.ui.fragments.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
import com.example.omarket.ui.fragments.Color;
import com.example.omarket.ui.fragments.SingletonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class SettingFragment extends SingletonFragment {

    private static SettingFragment settingFragment;

    private SettingFragment() {
        settingFragment = this;
        bottomNavigationItem = getView().findViewById(R.id.navigation_menu_settings);
    }

    public static SingletonFragment getInstance() {
        if (settingFragment != null){
            return settingFragment;
        }
        return new SettingFragment();
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