package com.example.omarket.ui.productFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.omarket.R;
import com.example.omarket.ui.NavigationFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends NavigationFragment implements TabLayoutMediator.TabConfigurationStrategy {

    public static ProductFragment newInstance(String param1, String param2) {
        return new ProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        ImageView productImage = view.findViewById(R.id.product_fragment_image_view_product);
        TabLayout tabLayout = view.findViewById(R.id.product_fragment_tabLayout);
        TabItem descriptionTab = view.findViewById(R.id.product_view_tab_description);
        TabItem informationTab = view.findViewById(R.id.product_view_tab_information);

        ViewPager2 viewPager = view.findViewById(R.id.product_fragment_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayoutMediator layoutMediator;
        layoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, this);
        layoutMediator.attach();

        return view;
    }


    @Override
    public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int i) {
        tab.setText("Tab : " + (i + 1));
    }
}