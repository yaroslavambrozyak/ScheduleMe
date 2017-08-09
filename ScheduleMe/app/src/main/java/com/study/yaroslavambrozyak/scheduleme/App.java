package com.study.yaroslavambrozyak.scheduleme;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;

import com.study.yaroslavambrozyak.scheduleme.modul.AppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.AppModule;
import com.study.yaroslavambrozyak.scheduleme.modul.DaggerAppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.MainPresenterModule;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class App extends Application {

    private static App app;
    private AppComponent appComponent;
    private SimpleDateFormat dateFormat;
    private Realm realm;
    private AlarmManager alarmManager;
    private Calendar calendar;

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = createComponent();
        dateFormat = new SimpleDateFormat("dd:MM:yyyy");
        realm = Realm.getInstance(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        /*realm.beginTransaction();
        realm.clear(Remind.class);
        realm.commitTransaction();
        */
    }

    public static App getApp() {
        return app;
    }

    public AppComponent getComponent() {
        return appComponent;
    }

    public Realm getRealm(){
        return realm;
    }

    @SuppressLint("SimpleDateFormat")
    public SimpleDateFormat getSimpleDateFormat() {
        return dateFormat;
    }

    public AlarmManager getAlarmManager(){
        return alarmManager;
    }

    public Calendar getCalendar(){
        return calendar;
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .mainPresenterModule(new MainPresenterModule())
                .build();
    }


}
