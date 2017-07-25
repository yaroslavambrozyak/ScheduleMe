package com.study.yaroslavambrozyak.scheduleme.interactor;


import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainInteractorImp implements MainInteractor {

    private List<Remind> mock;

    public MainInteractorImp(){
        mock = new ArrayList<>();
        Remind remind1 = new Remind(1,"firstRemind","Description",new Date());
        Remind remind2 = new Remind(2,"secondRemind","Description",new Date());
        Remind remind3 = new Remind(3,"thirdLongRemind","Description : asdasdasdasd" +
                "slg;ahg;oiarit;rkdng;kng;ifgh;idlh;lkrlkndldlfkjdjhitlitnykns" +
                "lhfdlihgdkglfihdtkdngka;ig;lskng;lin;iangalgnalk;fg'laig'aut' ",new Date());
        Remind remind4 = new Remind(4,"secondRemind","Description",new Date());
        Remind remind5 = new Remind(5,"secondRemind","Description",new Date());
        Remind remind6 = new Remind(6,"secondRemind","Description",new Date());
        mock.add(remind1);
        mock.add(remind2);
        mock.add(remind3);
        mock.add(remind4);
        mock.add(remind5);
        mock.add(remind6);
    }

    @Override
    public List<Remind> getReminds() {
        return mock;
    }

    @Override
    public void addRemind(Remind remind) {
        mock.add(remind);
    }
}
