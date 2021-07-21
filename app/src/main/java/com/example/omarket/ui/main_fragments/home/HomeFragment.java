package com.example.omarket.ui.main_fragments.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.main_fragments.MainActivity;
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        products = new ArrayList<>(); // get from server TODO
        activity = getActivity();
        category(1);


        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.homenavigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
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
        if (i == 1) {  // open all product
            MainActivity.loadProducts(new ServerCallback<Object>() {
                @Override
                public void onComplete(Result<Object> result) {
                    if (result instanceof Result.Success) {
                        products.clear();
                        products.addAll(Product.allProducts);
                    } else {
                        Toast.makeText(activity, "Loading products failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 2) {  // open my product
            products.clear();
//            MainActivity.loadProducts();
            for (Product p : Product.allProducts) {
                if (p.userEmail.equals(User.currentLoginUser.emailAddress)) {
                    products.add(p);
                }
            }

        } else if (i == 3) { // open electronic
            products.clear();
//            MainActivity.loadProducts();
            for (Product p : Product.allProducts) {
                if (p.categorySlug.equals("electronic")) {
                    products.add(p);
                }
            }
        } else if (i == 4) { // open fashion
            products.clear();
//            MainActivity.loadProducts();
            for (Product p : Product.allProducts) {
                if (p.categorySlug.equals("fashion")) {
                    products.add(p);
                }
            }
        } else if (i == 5) { // open industrial
            products.clear();
//            MainActivity.loadProducts();
            for (Product p : Product.allProducts) {
                if (p.categorySlug.equals("industrial")) {
                    products.add(p);
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }
}