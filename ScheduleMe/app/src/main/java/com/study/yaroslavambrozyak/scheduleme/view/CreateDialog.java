package com.study.yaroslavambrozyak.scheduleme.view;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.study.yaroslavambrozyak.scheduleme.utils.AlarmReceiver;
import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.utils.Constant;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.DateSetter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.TimeSetter;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateDialog extends DialogFragment
        implements DialogInterface.OnClickListener, DateSetter, TimeSetter {


    @BindView(R.id.edit_create_title)
    EditText editTitle;
    @BindView(R.id.edit_create_description)
    EditText editDescription;
    @BindView(R.id.text_date)
    TextView textViewDate;
    @BindView(R.id.text_clock)
    TextView textViewTime;

    private MainPresenter presenter;
    private Calendar calendar;
    private static final String TAG = "createTag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = App.getApp().getCalendar();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_new_remind);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        builder.setPositiveButton(R.string.dialog_create_remind_button, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        Date date = calendar.getTime();
        presenter.addRemind(title,description,date);
    }

    @OnClick(R.id.text_date)
    public void onTextDateClick() {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        dialog.setDateSetter(this);
        dialog.show(getFragmentManager(), TAG);
    }

    @OnClick(R.id.text_clock)
    public void onTextTimeClick(){
        TimePickerDialogFragment dialog = new TimePickerDialogFragment();
        dialog.setTimeSetter(this);
        dialog.show(getFragmentManager(),TAG);
    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        @SuppressLint("DefaultLocale")
        String date = String.format(Constant.DATE_FORMAT, dayOfMonth, month + 1, year);
        textViewDate.setText(date);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setTime(int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        @SuppressLint("DefaultLocale")
        String time = String.format(Constant.TIME_FORMAT,hourOfDay,minute);
        textViewTime.setText(time);
    }
}
