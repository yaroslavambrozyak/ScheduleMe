package com.study.yaroslavambrozyak.scheduleme.presenter;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.interactor.MainInteractorImp;
import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.utils.AlarmReceiver;
import com.study.yaroslavambrozyak.scheduleme.utils.Constant;
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
        RealmResults<Remind> reminds = realm.allObjects(Remind.class);
        if (reminds.size() == 0)
            view.showMessageEmptyList();
        return reminds;
    }

    @Override
    public void addRemind(String title, String description, Date date) {
        realm.beginTransaction();
        Remind remind = realm.createObject(Remind.class);
        long id = realm.where(Remind.class).maximumInt(Constant.USER_ID) + 1;
        remind.setId(id);
        remind.setTitle(title);
        remind.setDescription(description);
        remind.setDate(date);
        realm.commitTransaction();
        createAlarm(id,title, description, date);
    }

    @Override
    public void removeRemind(long id) {
        realm.beginTransaction();
        RealmResults<Remind> remind = realm.where(Remind.class).equalTo(Constant.USER_ID, id).findAll();
        removeAlarm(remind.first().getId(), remind.first().getTitle(), remind.first().getDescription());
        remind.clear();
        realm.commitTransaction();
    }

    private void createAlarm(long id,String title, String description, Date date) {
        Intent intent = new Intent(App.getApp(), AlarmReceiver.class);
        intent.putExtra(Constant.REMIND_TITLE, title);
        intent.putExtra(Constant.REMIND_DESCRIPTION, description);
        PendingIntent pint = PendingIntent.getBroadcast(App.getApp(), (int) id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = App.getApp().getAlarmManager();
        manager.set(AlarmManager.RTC_WAKEUP, date.getTime(), pint);
    }

    private void removeAlarm(long id,String title, String description) {
        Intent intent = new Intent(App.getApp(), AlarmReceiver.class);
        intent.putExtra(Constant.REMIND_TITLE, title);
        intent.putExtra(Constant.REMIND_DESCRIPTION, description);
        PendingIntent pint = PendingIntent.getBroadcast(App.getApp(), (int) id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = App.getApp().getAlarmManager();
        manager.cancel(pint);
    }

}
