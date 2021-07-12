package com.example.omarket.ui.main_fragments.home;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.ui.main_fragments.Color;
import com.example.omarket.ui.NavigationFragment;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends NavigationFragment {

    public HomeFragment() {
        super();
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        HomeAdapter homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }

}