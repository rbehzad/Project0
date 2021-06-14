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
        navigationButtonSettingId = R.id.navigation_bottom_settings;
        navigationButtonHomeId = R.id.navigation_bottom_home;
        navigationButtonFavoriteId = R.id.navigation_bottom_favorite;
        navigationButtonDashBoardId = R.id.navigation_bottom_dashboard;
        navigationButtonCategoriesId = R.id.navigation_bottom_products;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.navigation_host_button);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        loadFragment(HomeFragment.getInstance());



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_bottom_settings:
                loadFragment(SettingFragment.getInstance());
                break;
            case R.id.navigation_bottom_dashboard:
                loadFragment(DashboardFragment.getInstance());
                break;
            case R.id.navigation_bottom_home:
                loadFragment(HomeFragment.getInstance());
                break;
            case R.id.navigation_bottom_favorite:
                loadFragment(FavoriteFragment.getInstance());
                break;
            case R.id.navigation_bottom_products:
                loadFragment(ProductsFragment.getInstance());
                break;

            default:
                return false;

        }
        return true;
    }

    private void loadFragment(SingletonFragment singletonFragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (fragmentTransaction.isEmpty()){
            fragmentTransaction.add(R.id.container_navigation_bottom,singletonFragment);
        }
        else{
            fragmentTransaction.add(R.id.container_navigation_bottom,singletonFragment);
        }
        fragmentTransaction.commit();
    }

}

