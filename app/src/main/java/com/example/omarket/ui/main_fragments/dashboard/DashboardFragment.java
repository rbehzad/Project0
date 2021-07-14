package com.example.omarket.ui.main_fragments.dashboard;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.omarket.R;
import com.example.omarket.ui.NavigationFragment;
import org.jetbrains.annotations.NotNull;


import android.widget.Button;
import android.widget.ImageView;

import android.database.Cursor;
import android.graphics.BitmapFactory;

import android.provider.MediaStore;



public class DashboardFragment extends NavigationFragment {
    private static final int PICK_IMAGE = 100;
    Button button;
    ImageView image;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard,container,false);
    }
    AppCompatActivity appCompatActivity = new AppCompatActivity();
    private static int RESULT_LOAD_IMAGE = 1;
    View view;
    //////////////////////////////////////
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCompatActivity.setContentView(R.layout.fragment_dashboard);
        Button buttonLoadImage = (Button) view.findViewById(R.id.btnChangeImage);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == appCompatActivity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = appCompatActivity.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
/////////////////////////////////////////
    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }

}