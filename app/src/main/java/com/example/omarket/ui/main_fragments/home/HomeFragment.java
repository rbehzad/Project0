package com.example.omarket.ui.main_fragments.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.ui.NavigationFragment;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends NavigationFragment {
    TextView recyclerV;
    View view;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        List<Product> products = new ArrayList<>(); // get from server TODO
        for(int i=0 ;i<20; ++i) {
            products.add(new Product("oven", "20,000", "11111", "mike", "0921", "image", "info"));

        }
        HomeAdapter homeAdapter = new HomeAdapter(products);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // home ==> add product

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }

}