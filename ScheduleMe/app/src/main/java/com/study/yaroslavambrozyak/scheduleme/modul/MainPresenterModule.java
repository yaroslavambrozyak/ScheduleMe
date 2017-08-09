package com.study.yaroslavambrozyak.scheduleme.modul;


import com.study.yaroslavambrozyak.scheduleme.presenter.MainPresenterImp;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter(MainView view) {
        return new MainPresenterImp(view);
    }

}
