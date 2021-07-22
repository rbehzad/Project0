package com.example.omarket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.omarket.ui.main_fragments.Color;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_forgot_password#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_forgot_password extends Fragment implements View.OnClickListener{
    EditText emailAddress, newPassword;
    String userEmail;
    String newPass;
    Button forgotButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_forgot_password() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_forgot_password.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_forgot_password newInstance(String param1, String param2) {
        fragment_forgot_password fragment = new fragment_forgot_password();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        emailAddress = view.findViewById(R.id.registered_emailid);
        newPassword = view.findViewById(R.id.newPassword);
        newPass = newPassword.getText().toString().trim(); // take new password from here
        forgotButton = view.findViewById(R.id.forgot_button);
        forgotButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgot_button:
                userEmail = emailAddress.getText().toString().trim();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ userEmail});
                email.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password");
                email.putExtra(Intent.EXTRA_TEXT, "11111");
                //need this to prompts email client only
                email.setType("message/246135");
                startActivity(Intent.createChooser(email, userEmail));
        }
    }

}