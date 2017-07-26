package com.study.yaroslavambrozyak.scheduleme.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.yaroslavambrozyak.scheduleme.App;

import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.text.SimpleDateFormat;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.ViewHolder>{

    private RealmResults<Remind> remindList;

    public RemindAdapter(RealmResults<Remind> remindList) {
        this.remindList = remindList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_remind,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Remind remind = remindList.get(position);
        SimpleDateFormat simpleDateFormat = App.getApp().getSimpleDateFormat();
        holder.title.setText(remind.getTitle());
        holder.description.setText(remind.getDescription());
        holder.date.setText(simpleDateFormat.format(remind.getDate()));
    }

    @Override
    public int getItemCount() {
        return remindList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.remind_title);
            description = (TextView) itemView.findViewById(R.id.remind_description);
            date = (TextView) itemView.findViewById(R.id.remind_date);
        }
    }
}
