package com.study.yaroslavambrozyak.scheduleme;


import android.annotation.SuppressLint;
import android.app.Application;


import com.study.yaroslavambrozyak.scheduleme.modul.AppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.AppModule;
import com.study.yaroslavambrozyak.scheduleme.modul.DaggerAppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.MainPresenterModule;

import java.text.SimpleDateFormat;

import io.realm.Realm;

public class App extends Application {

    private static App app;
    private AppComponent appComponent;
    private SimpleDateFormat dateFormat;

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = createComponent();
        dateFormat = new SimpleDateFormat("dd:MM:yyyy");
    }

    public static App getApp() {
        return app;
    }

    public AppComponent getComponent() {
        return appComponent;
    }

    @SuppressLint("SimpleDateFormat")
    public SimpleDateFormat getSimpleDateFormat() {
        return dateFormat;
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .mainPresenterModule(new MainPresenterModule())
                .build();
    }


}
