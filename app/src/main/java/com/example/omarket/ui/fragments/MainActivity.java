package com.example.omarket.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.omarket.R;

import com.example.omarket.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
//    private BottomNavigationView bottomNavigation;
//    private NavController navController;
//    private NavHostFragment navHostFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

//        bottomNavigation = findViewById(R.id.nav_host_bottom);
//        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        navController = navHostFragment.getNavController();
//        NavigationUI.setupWithNavController(bottomNavigation,navController);


//        bottomNavigation = findViewById(R.id.navigation_host_button);
//        bottomNavigation.setOnNavigationItemSelectedListener(this);

//        loadFragment(HomeFragment.getInstance());


    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.navigation_bottom_settings:
//                loadFragment(SettingFragment.getInstance());
//                break;
//            case R.id.navigation_bottom_dashboard:
//                loadFragment(DashboardFragment.getInstance());
//                break;
//            case R.id.navigation_bottom_home:
//                loadFragment(HomeFragment.getInstance());
//                break;
//            case R.id.navigation_bottom_favorite:
//                loadFragment(FavoriteFragment.getInstance());
//                break;
//            case R.id.navigation_bottom_products:
//                loadFragment(ProductsFragment.getInstance());
//                break;
//
//            default:
//                return false;
//
//        }
//        return true;
//    }
//
//    private void loadFragment(SingletonFragment singletonFragment){
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//
//        if (fragmentTransaction.isEmpty()){
//            fragmentTransaction.add(R.id.container_navigation_bottom,singletonFragment);
//        }
//        else{
//            fragmentTransaction.add(R.id.container_navigation_bottom,singletonFragment);
//        }
//        fragmentTransaction.commit();
//    }

}

