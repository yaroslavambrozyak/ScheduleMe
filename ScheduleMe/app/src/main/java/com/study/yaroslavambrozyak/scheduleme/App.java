package com.study.yaroslavambrozyak.scheduleme;


import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;


import com.study.yaroslavambrozyak.scheduleme.model.Remind;
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
    private Realm realm;

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CreateApp","CreateApp");
        app = this;
        appComponent = createComponent();
        dateFormat = new SimpleDateFormat("dd:MM:yyyy");
        realm = Realm.getInstance(this);

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

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .mainPresenterModule(new MainPresenterModule())
                .build();
    }


}
