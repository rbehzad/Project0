package com.example.omarket.ui.productFragment;

import android.graphics.pdf.PdfRenderer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.omarket.ui.productFragment.tabLayoutFragment.DescriptionFragment;
import com.example.omarket.ui.productFragment.tabLayoutFragment.InformationFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull @NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int i) {
        switch (i){
            case 0:
                return DescriptionFragment.getInstance();
            case 1:
                return InformationFragment.getInstance();
            default:
                return DescriptionFragment.getInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
