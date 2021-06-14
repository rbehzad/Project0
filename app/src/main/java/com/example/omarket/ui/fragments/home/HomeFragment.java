package com.example.omarket.ui.fragments.home;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.omarket.R;
import com.example.omarket.databinding.FragmentHomeBinding;
import com.example.omarket.ui.fragments.Color;
import com.example.omarket.ui.fragments.SingletonFragment;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends SingletonFragment {

    private static HomeFragment homeFragment;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private HomeFragment(){
        homeFragment = this;
    }

    public static SingletonFragment getInstance() {
        if (homeFragment != null){
            return homeFragment;
        }
        return new HomeFragment();
    }

    @Override
    public void changeColorTo(Color color) {

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.titleHomeTextView;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}