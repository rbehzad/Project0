package com.example.omarket.ui.main_fragments.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.main_fragments.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends NavigationFragment {

    public List<Product> products;
    public FavoriteAdapter favoriteAdapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        products = new ArrayList<>();


        favoriteAdapter = new FavoriteAdapter(products);
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public void reloadAdapter(){
        favoriteAdapter.notifyDataSetChanged();
    }

    public void loadProducts(){
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        APIHandler.getAllFavoriteApi(new ServerCallback<ArrayList<String>>() {
            @Override
            public void onComplete(Result<ArrayList<String>> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success){
                    ArrayList<String> slugs = ((Result.Success<ArrayList<String>>) result).data;
                    favoriteAdapter.products.clear();
                    for(Product product: Product.allProducts){
                        boolean is_in = false;
                        for(String slug: slugs){
                            if (slug.equals(product.id)){
                                is_in = true;
                                break;
                            }
                        }
                        if (is_in) {
                            favoriteAdapter.products.add(product);
                        }
                    }
                } else if (result instanceof Result.Error){
                    Toast.makeText(getActivity(), "Loading favorite products failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProducts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }
}