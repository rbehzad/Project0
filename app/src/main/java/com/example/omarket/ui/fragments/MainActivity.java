package com.example.omarket.ui.fragments;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.omarket.R;
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
        navigationButtonCategoriesId = R.id.navigation_menu_categories;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.navigation_host_button);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        if (item == null) return false;
        switch (item.getItemId()){

            case R.id.navigation_menu_settings:
                changeFragmentTo(R.id.navigation_menu_settings, SettingFragment.getInstance());
                break;
            case R.id.navigation_menu_dashboard:
                changeFragmentTo(R.id.navigation_menu_settings, SettingFragment.getInstance());
                break;
            case R.id.navigation_menu_home:
                changeFragmentTo(R.id.navigation_menu_settings, SettingFragment.getInstance());
                break;
            case R.id.navigation_menu_favorite:
                changeFragmentTo(R.id.navigation_menu_settings, SettingFragment.getInstance());
                break;
            case R.id.navigation_menu_categories:
                changeFragmentTo(R.id.navigation_menu_settings, SettingFragment.getInstance());
                break;

            default:
                return false;

        }
        return true;
    }

    private void changeFragmentTo(int viewId, SingletonFragment singletonFragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(viewId,singletonFragment);
        fragmentTransaction.commit();
    }

}

