package com.example.omarket.ui.main_fragments.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.ui.NavigationFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends NavigationFragment {


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        List<Product> products = new ArrayList<>(); // get from server TODO
        // all product

        //
        for(int i=0 ;i<8; ++i) {
            products.add(new Product("oven", "20,000", "11111", "mike", "0921"));
        }
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(products);
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }
}