package com.study.yaroslavambrozyak.scheduleme.view.interfaces;


import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.util.List;

public interface MainView {
    void showProgress();
    void hideProgress();
    void showMessage(String message);
}