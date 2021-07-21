package com.example.omarket.ui.productFragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.ui.NavigationFragment;

import java.util.HashMap;
import java.util.Map;


public class AddProduct extends NavigationFragment implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE = 1;
    View view;
    ImageView imageView;
    EditText titleText, costText, descriptionText;
    Button button , btnSave;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ProgressBar progressBar;

    public AddProduct() {
        // Required empty public constructor
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
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
    private Animation shakeAnimation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        shakeAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
        titleText = view.findViewById(R.id.edit_text_title);
        descriptionText = view.findViewById(R.id.edit_text_description);
        costText = view.findViewById(R.id.edit_text_cost);
        titleText.setAnimation(shakeAnimation);
        costText.setAnimation(shakeAnimation);
        descriptionText.setAnimation(shakeAnimation);
        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        button = (Button) view.findViewById(R.id.buttonLoadPicture2);
        imageView = (ImageView) view.findViewById(R.id.addproduct_fragment_image_view_product);
        progressBar = view.findViewById(R.id.progressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        return view;
    }

    private void viewFailed(View v) {
        v.startAnimation(shakeAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                if (titleText.getText()==null || titleText.getText().toString().trim().equals("")||
                    descriptionText.getText()==null || descriptionText.getText().toString().trim().equals("")||
                    costText.getText()==null || costText.getText().toString().trim().equals("")) {
                    viewFailed(titleText);
                    viewFailed(descriptionText);
                    viewFailed(costText);
                }
                Map<String, Object> body = new HashMap<>();
                body.put("title",titleText.getText().toString());
                body.put("description",descriptionText.getText().toString());
                body.put("cost",costText.getText().toString());
                changeVisibilityTo(progressBar, View.VISIBLE);
                APIHandler.sendRequestOrGet(new ServerCallback<String>() {
                    @Override
                    public void onComplete(Result<String> result) {
                        changeVisibilityTo(progressBar, View.INVISIBLE);
                        if (result instanceof Result.Success){
                            Toast.makeText(getActivity(), "adding product complete", Toast.LENGTH_SHORT).show();
                            navigateFromViewTo(getView(),R.id.action_addProductFragment_to_homeFragment2);
                        } else {
                            Toast.makeText(getActivity(), "adding product failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, getActivity(), body, "AP");
                break;
        }
    }

    public void changeVisibilityTo(View v, int visibility) {
        v.setVisibility(visibility);
    }

}