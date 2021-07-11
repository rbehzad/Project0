package com.example.omarket.ui.main_fragments.home;

import android.app.admin.SystemUpdatePolicy;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.ui.main_fragments.products.ProductsFragment;

import org.jetbrains.annotations.NotNull;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Product> products;
    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        public HomeViewHolder(@NonNull@NotNull View itemView) {
            super(itemView);
        }
    }
}
