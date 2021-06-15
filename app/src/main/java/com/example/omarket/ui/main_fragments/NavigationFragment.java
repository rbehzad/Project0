package com.example.omarket.ui.main_fragments;

import androidx.fragment.app.Fragment;

import com.example.omarket.backend.commands.SaveCommand;

public abstract class NavigationFragment extends Fragment {
    protected SaveCommand saveCommand;

    public NavigationFragment() {
        this.saveCommand = SaveCommand.getInstance();
    }

    abstract public void changeColorTo(Color color);

}
