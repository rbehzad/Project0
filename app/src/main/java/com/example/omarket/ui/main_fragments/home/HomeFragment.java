package com.example.omarket.ui.main_fragments.home;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends NavigationFragment {
    List<Product> products;
    TextView recyclerV;
    View view;
    Activity activity;
    MenuView.ItemView electronic;
    MenuView.ItemView fasion;
    MenuView.ItemView industrial;
    MenuView.ItemView myProduct;
    MenuView.ItemView allProduct;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        products = new ArrayList<>(); // get from server TODO
        products.add(new Product("oven", 20000, "image", "info", User.currentLoginUser));
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.homenavigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.allproductid:
                        category(1);
                    case R.id.myproductid:
                        category(2);
                    case R.id.electronicsid:
                        category(3);
                    case R.id.fashionid:
                        category(4);
                    case R.id.industrialid:
                        category(5);
                }
                return true;
            }
        });
        HomeAdapter homeAdapter = new HomeAdapter(products);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    public void category(int i) {
        if(i==1) {  // open all product
            for (int j = 0; j < 20; ++j) {
                products.clear();
            }
        }
        else if(i==2) {  // open my product
            products.clear();
        }
        else if(i==3) { // open electronic
            products.clear();
        }
        else if(i==4) { // open fasion
            products.clear();
        }
        else if(i==5) { // open industrial
            products.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }
}