package com.example.omarket.ui.productFragment.tabLayoutFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omarket.R;
public class EditFragment extends Fragment {

    static private EditFragment editFragment;

    public static EditFragment getInstance() {
        if (editFragment != null){
            return editFragment;
        }
        else {
            editFragment = new EditFragment();
            return editFragment;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }
}