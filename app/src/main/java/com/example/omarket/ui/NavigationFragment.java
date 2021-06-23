package com.example.omarket.ui;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.omarket.backend.handlers.commands.SaveCommand;
import com.example.omarket.ui.main_fragments.Color;

public abstract class NavigationFragment extends Fragment {
    protected SaveCommand saveCommand;

    public NavigationFragment() {
        this.saveCommand = SaveCommand.getInstance();
    }

    public void navigateFromViewTo(View view, int navigationId) {
        Navigation.findNavController(view).navigate(navigationId);
    }

    public void changeIconColorTo(Color color) {

    }

}
