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
    public List<Product> products;
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
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    boolean i = false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
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
                        break;
                    case R.id.myproductid:
                        category(2);
                        break;
                    case R.id.electronicsid:
                        category(3);
                        break;
                    case R.id.fashionid:
                        category(4);
                        break;
                    case R.id.industrialid:
                        category(5);
                        break;
                }
                return true;
            }
        });

        homeAdapter = new HomeAdapter(products);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public void loadProducts(){
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        category(1);
    }

    public void category(int i) {
        if (i == 1) {  // open all product
            MainActivity.loadProducts(new ServerCallback<Object>() {
                @Override
                public void onComplete(Result<Object> result) {
                    if (result instanceof Result.Success) {
                        homeAdapter.products.clear();
                        homeAdapter.products.addAll(Product.allProducts);
                        loadProducts();
                    } else {
                        Toast.makeText(activity, "Loading products failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 2) {  // open my product
            MainActivity.loadProducts(new ServerCallback<Object>() {
                @Override
                public void onComplete(Result<Object> result) {
                    if (result instanceof Result.Success) {
                        homeAdapter.products.clear();
                        for (Product product: Product.allProducts){
                            if (product.userEmail.trim().equals(User.currentLoginUser.emailAddress.trim()))
                                homeAdapter.products.add(product);
                        }
                        loadProducts();
                    } else {
                        Toast.makeText(activity, "Loading products failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else if (i == 3) { // open electronic
            MainActivity.loadProducts(new ServerCallback<Object>() {
                @Override
                public void onComplete(Result<Object> result) {
                    if (result instanceof Result.Success) {
                        homeAdapter.products.clear();
                        for (Product p : Product.allProducts) {
                            if (p.categorySlug.equals("electronic")) {
                                homeAdapter.products.add(p);
                            }
                        }
                        loadProducts();
                    } else {
                        Toast.makeText(activity, "Loading products failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 4) { // open fashion
            MainActivity.loadProducts(new ServerCallback<Object>() {
                @Override
                public void onComplete(Result<Object> result) {
                    if (result instanceof Result.Success) {
                        homeAdapter.products.clear();
                        for (Product p : Product.allProducts) {
                            if (p.categorySlug.equals("fashion")) {
                                homeAdapter.products.add(p);
                            }
                        }
                        loadProducts();
                    } else {
                        Toast.makeText(activity, "Loading products failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 5) { // open industrial
            MainActivity.loadProducts(new ServerCallback<Object>() {
                @Override
                public void onComplete(Result<Object> result) {
                    if (result instanceof Result.Success) {
                        homeAdapter.products.clear();
                        for (Product p : Product.allProducts) {
                            if (p.categorySlug.equals("industrial")) {
                                homeAdapter.products.add(p);
                            }
                        }
                        loadProducts();
                    } else {
                        Toast.makeText(activity, "Loading products failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }
}