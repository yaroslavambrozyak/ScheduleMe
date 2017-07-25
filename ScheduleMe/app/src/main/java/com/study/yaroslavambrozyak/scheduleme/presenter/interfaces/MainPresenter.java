package com.study.yaroslavambrozyak.scheduleme.presenter.interfaces;


import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.util.List;

public interface MainPresenter {
    List<Remind> getReminds();
    void addRemind(Remind remind);
}
