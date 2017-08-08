package com.study.yaroslavambrozyak.scheduleme.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private MainPresenter presenter;

    private static final String TAG = "mainTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment fragmentById = (MainFragment) fragmentManager.findFragmentById(R.id.fragment_remind);
        presenter = fragmentById.presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @OnClick(R.id.fab)
    public void onFloatingButtonClick(View view) {
        CreateDialog dialogFragment = new CreateDialog();
        dialogFragment.setPresenter(presenter);
        dialogFragment.show(getFragmentManager(),TAG);
    }

    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }
}
