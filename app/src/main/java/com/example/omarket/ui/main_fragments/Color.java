package com.example.omarket.ui.main_fragments;

import android.view.View;
import android.widget.TextView;

public enum Color {
    WHITE(0, "FFFFFF"),
    BLACK(1, "000000"),
    Default(2,"320000");


    int id;
    String grb;

    private Color(int id, String grb) {
        this.id = id;
        this.grb = grb;
    }

    static private TextView textView;

    public static void changeViewColor(View v, int id) {
        if (v instanceof TextView) {
            textView = (TextView) v;
            textView.setTextColor(v.getResources().getColor(id));
        }

    }

    public static void changeHintViewColor(View v, int id) {
        if (v instanceof TextView) {
            textView = (TextView) v;
            textView.setHintTextColor(v.getResources().getColor(id));
        }

    }

}
