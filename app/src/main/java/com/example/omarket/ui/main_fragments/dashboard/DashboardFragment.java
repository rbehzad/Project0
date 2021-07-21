package com.example.omarket.ui.main_fragments.dashboard;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.omarket.R;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;

import org.jetbrains.annotations.NotNull;

//import com.example.omarket.backend.data.data.entities.User;


public class DashboardFragment extends NavigationFragment {
    View view;
    ImageView imageView;
    Button button;
    private static final int PICK_IMAGE = 100;
    private static int RESULT_LOAD_IMAGE = 1;
    Uri imageUri;
    TextView textViewName;
    TextView textViewEmail;
    TextView textViewPhone;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dashboard,container,false);
        textViewName = view.findViewById(R.id.dashboardTextview1);
        textViewEmail = view.findViewById(R.id.dashboardTextview2);
        textViewPhone = view.findViewById(R.id.dashboardTextview3);

//        Repository.getInstance(getContext()).getAllUsers(new RepositoryCallback<List<User>>() {
//            @Override
//            public void onComplete(Result<List<User>> result) {
//                if(result instanceof Result.Success) {  //((Result.Success<List<User>>) result).data.get(0).name
//                    textViewName.setText(User.currentLoginUser.fullName);
//                    textViewEmail.setText(User.currentLoginUser.emailAddress);
//                    textViewPhone.setText(User.currentLoginUser.phoneNumber);
//                }
//                else if(result instanceof Result.Error) {
//                    textViewName.setText(((Result.Error<List<User>>) result).exception.toString());
//                    textViewEmail.setText("Error");
//                    textViewPhone.setText("Error");
//                }
//            }
//        });


        button = (Button) view.findViewById(R.id.btnChangeImage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        imageView = (ImageView) view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textViewName.setText(User.currentLoginUser.fullName);
        textViewEmail.setText(User.currentLoginUser.emailAddress);
        textViewPhone.setText(User.currentLoginUser.phoneNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        saveCommand.execute();
    }

}