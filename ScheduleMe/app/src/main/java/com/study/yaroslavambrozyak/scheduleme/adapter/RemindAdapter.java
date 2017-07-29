package com.study.yaroslavambrozyak.scheduleme.adapter;


import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.study.yaroslavambrozyak.scheduleme.App;

import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<Remind> remindList;
    private RecyclerView recyclerView;
    private MainPresenter presenter;

    public RemindAdapter(RealmResults<Remind> remindList, RecyclerView recyclerView, MainPresenter presenter) {
        setHasStableIds(true);
        this.remindList = remindList;
        this.recyclerView = recyclerView;
        this.presenter = presenter;
        App.getApp().getRealm().addChangeListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_remind, parent, false);
        return new ViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(remindList.get(position));
    }

    @Override
    public int getItemCount() {
        return remindList.size();
    }

    @Override
    public long getItemId(int position) {
        return remindList.get(position).getId();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
        recyclerView.scrollToPosition(remindList.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.remind_title)
        TextView title;
        @BindView(R.id.remind_description)
        TextView description;
        @BindView(R.id.remind_date)
        TextView date;
        private MainPresenter presenter;
        private long id;

        public ViewHolder(View itemView, MainPresenter presenter) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.presenter = presenter;
        }

        public void bind(Remind remind) {
            id = remind.getId();
            SimpleDateFormat simpleDateFormat = App.getApp().getSimpleDateFormat();
            title.setText(remind.getTitle());
            description.setText(remind.getDescription());
            date.setText(simpleDateFormat.format(remind.getDate()));
        }

        @OnClick(R.id.card_button)
        public void onButtonClick(View view) {
            Context wrapper = new ContextThemeWrapper(App.getApp(),R.style.MyPopupMenu);
            PopupMenu popupMenu = new PopupMenu(wrapper, view);
            popupMenu.inflate(R.menu.menu_change_remind);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.delete_remind:
                            presenter.removeRemind(id);
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
        }
    }
}
