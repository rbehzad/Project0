package com.example.omarket.ui.fragments;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.omarket.ui.fragments.categories.ProductsFragment;
import com.example.omarket.R;
import com.example.omarket.ui.fragments.dashboard.DashboardFragment;
import com.example.omarket.ui.fragments.favorites.FavoriteFragment;
import com.example.omarket.ui.fragments.home.HomeFragment;
import com.example.omarket.ui.fragments.settings.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigation;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private final int navigationButtonSettingId;
    private final int navigationButtonHomeId;
    private final int navigationButtonFavoriteId;
    private final int navigationButtonDashBoardId;
    private final int navigationButtonCategoriesId;

    {
        navigationButtonSettingId = R.id.navigation_menu_settings;
        navigationButtonHomeId = R.id.navigation_menu_home;
        navigationButtonFavoriteId = R.id.navigation_menu_favorite;
        navigationButtonDashBoardId = R.id.navigation_menu_dashboard;
        navigationButtonCategoriesId = R.id.navigation_menu_products;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.navigation_host_button);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loadFragment(R.id.navigation_menu_home,HomeFragment.getInstance());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        if (item == null) return false;
        switch (item.getItemId()){

            case R.id.navigation_menu_settings:
                loadFragment(R.id.navigation_menu_settings, SettingFragment.getInstance());
                break;
            case R.id.navigation_menu_dashboard:
                loadFragment(R.id.navigation_menu_dashboard, DashboardFragment.getInstance());
                break;
            case R.id.navigation_menu_home:
                loadFragment(R.id.navigation_menu_home, HomeFragment.getInstance());
                break;
            case R.id.navigation_menu_favorite:
                loadFragment(R.id.navigation_menu_favorite, FavoriteFragment.getInstance());
                break;
            case R.id.navigation_menu_products:
                loadFragment(R.id.navigation_menu_products, ProductsFragment.getInstance());
                break;

            default:
                return false;

        }
        return true;
    }

    private void loadFragment(int viewId, SingletonFragment singletonFragment){
        fragmentTransaction.add(viewId,singletonFragment);
        fragmentTransaction.commit();
        singletonFragment.changeColorTo(Color.BLACK);
    }

}

