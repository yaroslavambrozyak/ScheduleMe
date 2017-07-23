package com.study.yaroslavambrozyak.scheduleme.presenter;


import com.study.yaroslavambrozyak.scheduleme.interactor.MainInteractorImp;
import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.MainView;

public class MainPresenterImp implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;
    
    public MainPresenterImp(MainView view){
        this.view = view;
        interactor = new MainInteractorImp();
    }

    @Override
    public void getReminds() {
        view.setReminds(interactor.getReminds());
        view.showMessage("done");
    }
}
