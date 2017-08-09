package com.study.yaroslavambrozyak.scheduleme.modul;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class, MainPresenterModule.class})
@Singleton
public interface AppComponent {

}
