package com.study.yaroslavambrozyak.scheduleme.presenter;


import com.study.yaroslavambrozyak.scheduleme.interactor.MainInteractorImp;
import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.MainView;

import java.util.List;

public class MainPresenterImp implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;
    
    public MainPresenterImp(MainView view){
        this.view = view;
        interactor = new MainInteractorImp();
    }

    @Override
    public List<Remind> getReminds() {
        view.showProgress();
        List<Remind> reminds = interactor.getReminds();
        view.hideProgress();
        return reminds;
    }

    @Override
    public void addRemind(Remind remind) {
        interactor.addRemind(remind);
        getReminds();
    }
}
