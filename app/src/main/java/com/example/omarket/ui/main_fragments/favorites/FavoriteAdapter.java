package com.example.omarket.ui.main_fragments.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omarket.R;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.user.User;
import com.example.omarket.backend.user.UserType;
import com.example.omarket.ui.NavigationFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    NavigationFragment navigationFragment;
    public List<Product> products;

    FavoriteAdapter(List<Product> products) { this.products = products; }
    @NonNull
    @NotNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavoriteViewHolder holder, int position) {
        holder.bind(products.get(position));
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
    TextView titleTextView, priceTextView;

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        View view;
        public FavoriteViewHolder(@NonNull@NotNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            priceTextView = itemView.findViewById(R.id.text_view_price);
            view = itemView;
        }
        public void bind(Product product) {
            titleTextView.setText(product.name);
            priceTextView.setText(String.valueOf(product.price));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // execute this method when user click card
                    Product.selectedProduct = product;
                    if (product.userEmail.equals(User.currentLoginUser.getEmailAddress())||
                            User.currentLoginUser.userType== UserType.SUPER_ADMIN) {
                        NavigationFragment.navigateFromViewTo(view, R.id.action_favoriteFragment_to_fragment_editId);
                    } else {
                        NavigationFragment.navigateFromViewTo(view, R.id.action_favoriteFragment_to_productFragment);
                    }
                }
            });
        }
    }
}
