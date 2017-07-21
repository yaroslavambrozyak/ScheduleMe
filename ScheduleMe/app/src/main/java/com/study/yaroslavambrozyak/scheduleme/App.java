package com.study.yaroslavambrozyak.scheduleme;


import android.app.Application;

import com.study.yaroslavambrozyak.scheduleme.modul.AppComponent;
import com.study.yaroslavambrozyak.scheduleme.modul.AppModule;
import com.study.yaroslavambrozyak.scheduleme.modul.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createComponent();
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


}
