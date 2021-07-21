package com.example.omarket.ui.productFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.omarket.R;
public class EditFragment extends Fragment implements View.OnTouchListener {
    EditText title;
    EditText description;
    EditText cost;

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
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
            title = view.findViewById(R.id.edit_fragment_editText_title);
            description = view.findViewById(R.id.edit_fragment_editText_description);
            cost = view.findViewById(R.id.edit_fragment_editText_cost);
            title.setOnTouchListener(this);
            description.setOnTouchListener(this);
            cost.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edit_fragment_editText_title:

                break;
            case R.id.edit_fragment_editText_description:

                break;
            case R.id.edit_fragment_editText_cost:

                break;
        }

        return false;
    }
}