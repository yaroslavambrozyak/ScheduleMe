package com.study.yaroslavambrozyak.scheduleme.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.adapter.RemindAdapter;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.MainPresenterImp;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainFragment extends Fragment implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_empty_list)
    TextView textEmptyList;

    //todo try to inject next time!
    MainPresenter presenter;
    private Context context;


    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenterImp(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageEmptyList() {
        textEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessageEmptyList() {
        textEmptyList.setVisibility(View.GONE);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private RealmResults<Remind> getReminds() {
        return presenter.getReminds();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        RecyclerView.Adapter adapter = new RemindAdapter(getReminds(), this, presenter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    showFloatButton();
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && getFloatButtonState())
                    hideFloatButton();
            }
        });
    }

    private void hideFloatButton() {
        ((MainActivity) getActivity()).getFloatingActionButton().hide();
    }

    private void showFloatButton() {
        ((MainActivity) getActivity()).getFloatingActionButton().show();
    }

    private boolean getFloatButtonState() {
        return ((MainActivity) getActivity()).getFloatingActionButton().isShown();
    }
}
