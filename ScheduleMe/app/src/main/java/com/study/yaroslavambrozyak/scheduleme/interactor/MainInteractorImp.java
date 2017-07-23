package com.study.yaroslavambrozyak.scheduleme.interactor;


import com.study.yaroslavambrozyak.scheduleme.interactor.interfaces.MainInteractor;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.util.ArrayList;
import java.util.List;

public class MainInteractorImp implements MainInteractor {
    @Override
    public List<Remind> getReminds() {
        return mock();
    }

    private List<Remind> mock() {
        List<Remind> mock = new ArrayList<>();
        Remind remind1 = new Remind(1,"firstRemind","Description");
        Remind remind2 = new Remind(2,"secondRemind","Description");
        Remind remind3 = new Remind(3,"thirdLongRemind","Description : asdasdasdasd" +
                "slg;ahg;oiarit;rkdng;kng;ifgh;idlh;lkrlkndldlfkjdjhitlitnykns" +
                "lhfdlihgdkglfihdtkdngka;ig;lskng;lin;iangalgnalk;fg'laig'aut' ");
        mock.add(remind1);
        mock.add(remind2);
        mock.add(remind3);
        return mock;
    }
}
