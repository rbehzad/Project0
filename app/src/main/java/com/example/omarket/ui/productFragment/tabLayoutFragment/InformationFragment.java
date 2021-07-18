package com.example.omarket.ui.productFragment.tabLayoutFragment;

import android.icu.text.IDNA;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;

public class InformationFragment extends Fragment {

    private static InformationFragment informationFragment;

    public static InformationFragment getInstance() {
        if (informationFragment != null){
            return informationFragment;
        }
        else {
            informationFragment = new InformationFragment();
            return informationFragment;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }
}