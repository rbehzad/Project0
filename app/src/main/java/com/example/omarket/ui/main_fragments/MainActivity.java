package com.example.omarket.ui.main_fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.omarket.R;


import com.example.omarket.backend.api.APIHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;
    private BottomNavigationView bottomNavigation;
    private NavController navController;
    private NavHostFragment navHostFragment;
    static ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        progressBar = findViewById(R.id.progressBar2);
        bottomNavigation = findViewById(R.id.nav_host_bottom);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigation,navController);
        loadProducts();

    }

    public static void loadProducts(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                APIHandler.getAllProductInfo(mainActivity);
                progressBar.setVisibility(View.INVISIBLE);

            }
        };
        progressBar.setVisibility(View.VISIBLE);
        thread.start();
    }

}

