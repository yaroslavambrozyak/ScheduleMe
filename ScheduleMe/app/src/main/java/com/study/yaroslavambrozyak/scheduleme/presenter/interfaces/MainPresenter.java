package com.study.yaroslavambrozyak.scheduleme.presenter.interfaces;


import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.model.RemindSettings;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;

public interface MainPresenter {
    RealmResults<Remind> getReminds();
    void addRemind(Remind remind, RemindSettings remindSettings);
    void removeRemind(long id);
}
