package com.example.omarket.ui.main_fragments;

public enum Color {
    WHITE(0,"FFFFFF"),
    BLACK(1,"000000");

    int id;
    String grb;
    private Color(int id,String grb){
        this.id = id;
        this.grb = grb;
    }
}
