package com.study.yaroslavambrozyak.scheduleme.presenter.interfaces;


import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;

public interface MainPresenter {
    RealmResults<Remind> getReminds();
    void addRemind(String title,String description,Date date);
    void removeRemind(long id);
}
