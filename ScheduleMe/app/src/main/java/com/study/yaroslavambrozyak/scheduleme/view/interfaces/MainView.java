package com.study.yaroslavambrozyak.scheduleme.view.interfaces;


import android.support.v7.widget.RecyclerView;

public interface MainView {
    void showMessage(String message);
    void showMessageEmptyList();
    void hideMessageEmptyList();
    RecyclerView getRecyclerView();
}
