package com.example.omarket.ui.fragments.dashboard;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
import com.example.omarket.ui.fragments.Color;
import com.example.omarket.ui.fragments.SingletonFragment;

public class DashboardFragment extends SingletonFragment {

    private static DashboardFragment dashboardFragment;

    private DashboardFragment(){
        dashboardFragment = this;
    }

    public static SingletonFragment getInstance() {
        if (dashboardFragment != null){
            return dashboardFragment;
        }
        return new DashboardFragment();
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