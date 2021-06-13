package com.example.omarket.ui.fragments.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
import com.example.omarket.ui.fragments.SingletonFragment;

public class SettingFragment extends SingletonFragment {

    private static SettingFragment settingFragment;

    private SettingFragment() {
        settingFragment = this;
    }

    public static SingletonFragment getInstance() {
        if (settingFragment != null){
            return settingFragment;
        }
        return new SettingFragment();
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