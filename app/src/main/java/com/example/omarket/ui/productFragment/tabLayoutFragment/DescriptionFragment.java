package com.example.omarket.ui.productFragment.tabLayoutFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;


public class DescriptionFragment extends Fragment {

    private static DescriptionFragment descriptionFragment;

    public static DescriptionFragment getInstance() {
        if (descriptionFragment != null){
            return descriptionFragment;
        }
        else {
            descriptionFragment = new DescriptionFragment();
            return descriptionFragment;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_description, container, false);
        return view;
    }



}