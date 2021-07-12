package com.example.omarket.ui;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.omarket.backend.handlers.commands.SaveCommand;
import com.example.omarket.ui.main_fragments.Color;

public abstract class NavigationFragment extends Fragment {
    protected SaveCommand saveCommand;

    public void navigateFromViewTo(View view , int id){
        Navigation.findNavController(view).navigate(id);
    }

}
