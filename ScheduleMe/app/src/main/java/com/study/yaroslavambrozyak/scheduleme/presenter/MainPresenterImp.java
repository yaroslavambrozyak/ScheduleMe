package com.study.yaroslavambrozyak.scheduleme.presenter;


import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.interactor.MainInteractorImp;
import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.MainView;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainPresenterImp implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;
    private Realm realm;

    public MainPresenterImp(MainView view) {
        this.view = view;
        realm = App.getApp().getRealm();
        interactor = new MainInteractorImp();
    }

    @Override
    public RealmResults<Remind> getReminds() {
        view.showProgress();
        RealmResults<Remind> reminds = realm.allObjects(Remind.class);
        view.hideProgress();
        return reminds;
    }

    @Override
    public void addRemind(String title, String description, Date date) {
        realm.beginTransaction();
        Remind remind = realm.createObject(Remind.class);
        long id = realm.where(Remind.class).maximumInt("id") + 1;
        remind.setId(id);
        remind.setTitle(title);
        remind.setDescription(description);
        remind.setDate(date);
        realm.commitTransaction();
        //getReminds();
    }
}
