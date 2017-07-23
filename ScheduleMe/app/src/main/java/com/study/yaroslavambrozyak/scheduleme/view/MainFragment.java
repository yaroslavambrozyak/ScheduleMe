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

public class MainFragment extends Fragment implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    //todo try to inject next time!
    private MainPresenter presenter;
    private List<Remind> reminds;
    private Context context;

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
        reminds = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        getReminds();
        return view;
    }

    @Override
    public void setReminds(List<Remind> remindsData) {
        reminds.addAll(remindsData);
        recyclerView.getAdapter().notifyDataSetChanged();
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

    private void getReminds(){
        presenter.getReminds();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        RecyclerView.Adapter adapter = new RemindAdapter(reminds);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
