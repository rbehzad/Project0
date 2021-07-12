package com.example.omarket.ui.main_fragments.dashboard;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.omarket.R;
import com.example.omarket.ui.main_fragments.Color;
import com.example.omarket.ui.NavigationFragment;
import org.jetbrains.annotations.NotNull;



public class DashboardFragment extends NavigationFragment {
    private static final int PICK_IMAGE = 100;
    public DashboardFragment() {
        super();
    }



    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard,container,false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }

}