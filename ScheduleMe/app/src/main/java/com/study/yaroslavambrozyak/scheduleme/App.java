package com.study.yaroslavambrozyak.scheduleme;


import android.app.Application;

import com.study.yaroslavambrozyak.scheduleme.modul.AppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.AppModule;
import com.study.yaroslavambrozyak.scheduleme.modul.DaggerAppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.MainPresenterModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createComponent();
    }

    public static AppComponent getComponent(){
        return appComponent;
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .mainPresenterModule(new MainPresenterModule())
                .build();
    }


}
