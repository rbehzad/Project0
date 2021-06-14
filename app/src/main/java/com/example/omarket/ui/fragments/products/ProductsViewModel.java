package com.example.omarket.ui.fragments.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ProductsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Products fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
