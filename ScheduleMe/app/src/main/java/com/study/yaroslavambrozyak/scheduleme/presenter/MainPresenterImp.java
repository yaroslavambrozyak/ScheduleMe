package com.study.yaroslavambrozyak.scheduleme.presenter;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.interactor.MainInteractorImp;
import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.model.RemindSettings;
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
        RealmResults<Remind> reminds = realm.allObjects(Remind.class).sort("date");
        if (reminds.size() == 0)
            view.showMessageEmptyList();
        return reminds;
    }

    @Override
    public void addRemind(Remind remind, RemindSettings remindSettings) {
        realm.beginTransaction();
        Remind remindObject = createRemindObject(remind);
        RemindSettings remindSettingsObject = createRemindSettings(remindSettings);
        remindObject.setRemindSettings(remindSettingsObject);
        realm.commitTransaction();
        if (remind.getDate().after(new Date()))
            createAlarm(remindObject,remindSettingsObject);
    }

    @Override
    public void removeRemind(long id) {
        realm.beginTransaction();
        RealmResults<Remind> remind = realm.where(Remind.class).equalTo(Constant.USER_ID, id).findAll();
        removeAlarm(remind.first().getId(), remind.first().getTitle(), remind.first().getDescription());
        remind.clear();
        realm.commitTransaction();
    }

    private void createAlarm(Remind remind, RemindSettings remindSettings) {
        Intent intent = new Intent(App.getApp(), AlarmReceiver.class);
        intent.putExtra(Constant.REMIND_TITLE, remind.getTitle());
        intent.putExtra(Constant.REMIND_DESCRIPTION, remind.getDescription());
        intent.putExtra(Constant.MUSIC_ENABLE,remindSettings.isMusicEnable());
        intent.putExtra(Constant.VIBRATION_ENABLE,remindSettings.isVibrationEnable());
        PendingIntent pint = PendingIntent.getBroadcast(App.getApp(), (int) remind.getId(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = App.getApp().getAlarmManager();
        manager.set(AlarmManager.RTC_WAKEUP, remind.getDate().getTime(), pint);
    }

    private void removeAlarm(long id, String title, String description) {
        Intent intent = new Intent(App.getApp(), AlarmReceiver.class);
        intent.putExtra(Constant.REMIND_TITLE, title);
        intent.putExtra(Constant.REMIND_DESCRIPTION, description);
        PendingIntent pint = PendingIntent.getBroadcast(App.getApp(), (int) id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = App.getApp().getAlarmManager();
        manager.cancel(pint);
    }

    private Remind createRemindObject(Remind remind) {
        Remind remindObject = realm.createObject(Remind.class);
        long idRemind = realm.where(Remind.class).maximumInt(Constant.USER_ID) + 1;
        remindObject.setId(idRemind);
        remindObject.setTitle(remind.getTitle());
        remindObject.setDescription(remind.getDescription());
        remindObject.setDate(remind.getDate());
        return remindObject;
    }

    private RemindSettings createRemindSettings(RemindSettings remindSettings) {
        RemindSettings remindSettingsObject = realm.createObject(RemindSettings.class);
        long idSettings = realm.where(RemindSettings.class).maximumInt("id") + 1;
        remindSettingsObject.setId(idSettings);
        remindSettingsObject.setVibrationEnable(remindSettings.isVibrationEnable());
        remindSettingsObject.setMusicEnable(remindSettings.isMusicEnable());
        return remindSettingsObject;
    }

}
