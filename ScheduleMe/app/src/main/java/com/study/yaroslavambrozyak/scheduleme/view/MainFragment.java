package com.study.yaroslavambrozyak.scheduleme.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.adapter.RemindAdapter;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.MainPresenterImp;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainFragment extends Fragment implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    //todo try to inject next time!
    MainPresenter presenter;
    private Context context;
    private Realm realm;

    public MainFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenterImp(this);
        realm = Realm.getInstance(context);
        realm.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    private RealmResults<Remind> getReminds(){
        return realm.allObjects(Remind.class);
        /*presenter.getReminds();*/
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        RecyclerView.Adapter adapter = new RemindAdapter(getReminds());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
