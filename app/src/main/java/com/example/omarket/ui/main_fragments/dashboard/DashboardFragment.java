package com.example.omarket.ui.main_fragments.dashboard;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.NavigationFragment;
import com.example.omarket.ui.main_fragments.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

//import com.example.omarket.backend.data.data.entities.User;


public class DashboardFragment extends NavigationFragment implements View.OnClickListener {
    TextView hosseinGmail;
    TextView rezaGmail;
    View view;
    ImageView imageView;
    Button button, signOut ,save;
    private static final int PICK_IMAGE = 100;
    private static int RESULT_LOAD_IMAGE = 1;
    Uri imageUri;
    TextView textViewName;
    TextView textViewEmail;
    EditText editTextPhone;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dashboard,container,false);
        textViewName = view.findViewById(R.id.dashboardTextview1);
        textViewEmail = view.findViewById(R.id.dashboardTextview2);
        editTextPhone = view.findViewById(R.id.dashboardTextviewPhoneNumber);
        hosseinGmail = view.findViewById(R.id.dashboardTextview6);
        rezaGmail = view.findViewById(R.id.dashboardTextview7);
        save = view.findViewById(R.id.dashboard_fragment_btn_save);
        save.setOnClickListener(this);
        hosseinGmail.setOnClickListener(this);
        rezaGmail.setOnClickListener(this);
        signOut = view.findViewById(R.id.dashboardFragment_btn_singOut);
        signOut.setOnClickListener(this);

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
        editTextPhone.setText(User.currentLoginUser.phoneNumber);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dashboardFragment_btn_singOut:
                User.currentLoginUser = new User();
                navigateFromViewTo(getView(),R.id.action_dashboardFragment_to_startActivity);
                break;
            case R.id.dashboardTextview6:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ hosseinGmail.getText().toString().trim()});
                email.putExtra(Intent.EXTRA_SUBJECT, "support");
                email.putExtra(Intent.EXTRA_TEXT, "body of email");
                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, hosseinGmail.getText().toString().trim()));
                break;
            case R.id.dashboardTextview7:
                Intent email2 = new Intent(Intent.ACTION_SEND);
                email2.putExtra(Intent.EXTRA_EMAIL, new String[]{ rezaGmail.getText().toString().trim()});
                email2.putExtra(Intent.EXTRA_SUBJECT, "support");
                email2.putExtra(Intent.EXTRA_TEXT, "body of email");
                //need this to prompts email client only
                email2.setType("message/rfc822");
                startActivity(Intent.createChooser(email2, rezaGmail.getText().toString().trim()));
                break;
            case R.id.dashboard_fragment_btn_save:
                updateUser();
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void updateUser() {
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        // category radio button
        body.put("phone_number", editTextPhone.getText().toString());
        body.put("first_name",User.currentLoginUser.fullName.subSequence(0,User.currentLoginUser.fullName.length()/2));
        body.put("last_name",User.currentLoginUser.fullName.subSequence(User.currentLoginUser.fullName.length()/2,User.currentLoginUser.fullName.length()));
        APIHandler.sendRequestOrGet(new ServerCallback<String>() {
            @Override
            public void onComplete(Result<String> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success) {
                    Toast.makeText(MainActivity.mainActivity, "Update user successful", Toast.LENGTH_SHORT).show();
                } else if (result instanceof Result.Error) {
                    Toast.makeText(MainActivity.mainActivity, "Update filed", Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.mainActivity, body, "UU");
    }

}