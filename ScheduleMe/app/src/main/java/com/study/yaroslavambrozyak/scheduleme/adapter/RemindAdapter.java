package com.study.yaroslavambrozyak.scheduleme.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import java.util.List;

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.ViewHolder> {

    private List<Remind> remindList;

    public RemindAdapter(List<Remind> remindList) {
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
        holder.title.setText(remind.getTitle());
        holder.description.setText(remind.getDescripton());
    }

    @Override
    public int getItemCount() {
        return remindList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.remind_title);
            description = (TextView) itemView.findViewById(R.id.remind_description);
        }
    }

}
