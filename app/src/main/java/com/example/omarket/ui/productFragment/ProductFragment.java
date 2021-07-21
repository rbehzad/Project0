package com.example.omarket.ui.productFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.omarket.R;
import com.example.omarket.backend.api.APIHandler;
import com.example.omarket.backend.product.Product;
import com.example.omarket.backend.response.Result;
import com.example.omarket.backend.response.ServerCallback;
import com.example.omarket.backend.user.User;
import com.example.omarket.ui.main_fragments.MainActivity;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    TextView title, description, cost, name, phoneNumber, email;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        title = view.findViewById(R.id.product_fragment_textView_title);
        description = view.findViewById(R.id.product_fragment_textView_description);
        cost = view.findViewById(R.id.product_fragment_textView_cost);

        name = view.findViewById(R.id.product_fragment_textView_full_name);
        phoneNumber = view.findViewById(R.id.product_fragment_textView_phone_number);
        email = view.findViewById(R.id.product_fragment_textView_email);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        title.setText(Product.selectedProduct.name);
        description.setText(Product.selectedProduct.description);
        cost.setText(Product.selectedProduct.price);
        final User[] user = {null};
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        HashMap<String, Object> body = new HashMap<>();
        body.put("email",Product.selectedProduct.userEmail);
        APIHandler.getUserInfoApi(new ServerCallback<User>() {
            @Override
            public void onComplete(Result<User> result) {
                MainActivity.progressBar.setVisibility(View.INVISIBLE);
                if (result instanceof Result.Success){
                    user[0] = ((Result.Success<User>) result).data;
                    email.setText(user[0].emailAddress);
                    phoneNumber.setText(user[0].phoneNumber);
                    name.setText(user[0].fullName);
                } else if (result instanceof Result.Error){
                    Toast.makeText(getActivity(), "Loading failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, getActivity(), body);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.product_fragment_favoritecheckBox:
                if (checked) {

                } else {

                }
                break;
        }
    }
}