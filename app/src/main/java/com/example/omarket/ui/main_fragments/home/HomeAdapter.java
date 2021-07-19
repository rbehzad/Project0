package com.example.omarket.ui.main_fragments.home;

import android.app.admin.SystemUpdatePolicy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.ui.productFragment.ProductActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Product> products;
    ProductActivity selected_fragment;
    HomeAdapter(List<Product> products) { this.products = products; }
    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_home, parent, false);
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
    TextView titleTextView, priceTextView;

    class HomeViewHolder extends RecyclerView.ViewHolder {
        View view;
        public HomeViewHolder(@NonNull@NotNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            priceTextView = itemView.findViewById(R.id.text_view_price);
            view = itemView;
        }
        public void bind(Product product) {
            titleTextView.setText(product.name);
            priceTextView.setText(product.price);
            // switch fragment(home ==> product)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // execute this method when user click card
                    selected_fragment = new ProductActivity();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_productId, selected_fragment).commit();
                }
            });
        }
        

    }
    public interface onProductListener {
        void onProductClick(int position);
    }
}
