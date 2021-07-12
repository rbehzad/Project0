package com.example.omarket.ui.main_fragments.home;

import android.app.admin.SystemUpdatePolicy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.ui.main_fragments.products.ProductsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Product> products;
    HomeAdapter(List<Product> products) {
        this.products = products;
    }
    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    TextView titleTextView, dueTextView;

    class HomeViewHolder extends RecyclerView.ViewHolder {
        View view;
        public HomeViewHolder(@NonNull@NotNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            dueTextView = itemView.findViewById(R.id.text_view_due);
            view = itemView;
        }
        public void bind(Product product) {
            titleTextView.setText(product.name);
            titleTextView.setText(product.name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // execute this method when user click card

                }
            });
        }
    }
}
