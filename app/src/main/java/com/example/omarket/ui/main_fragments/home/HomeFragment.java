package com.example.omarket.ui.main_fragments.home;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.fragment_addProduct;

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
    /*    switch fragment(home ==> product)
        // onclick recycler(switch fragment from fragement_home to fragment_product)
        recyclerV = view.findViewById(R.id.text_view_title);
        recyclerV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     //           Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productFragment);
                navigateFromViewTo(view, R.id.action_homeFragment_to_productFragment);
            }
        });
        */
        new AppCompatActivity() {
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addproductid:
                        FragmentTransaction transaction = getSupportFragmentManager()
                                .beginTransaction();
                        transaction.add(R.id.addProductFragment, targetFragment, targetFragment.getClass().getName());

                }
                return super.onOptionsItemSelected(item);
            }
        };
        return view;
    }
    public void clickpro(View view) { // for switch fragment(home ==> product)
   //     navigateFromViewTo(view, R.id.action_homeFragment_to_productFragment);
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productFragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }

}