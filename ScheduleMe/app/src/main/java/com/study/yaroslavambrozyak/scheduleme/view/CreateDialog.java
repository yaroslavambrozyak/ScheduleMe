package com.study.yaroslavambrozyak.scheduleme.view;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;
import com.study.yaroslavambrozyak.scheduleme.presenter.interfaces.MainPresenter;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.DateSetter;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class CreateDialog extends DialogFragment
        implements DialogInterface.OnClickListener, DateSetter {


    @BindView(R.id.edit_create_title)
    EditText editTitle;
    @BindView(R.id.edit_create_description)
    EditText editDescription;
    @BindView(R.id.text_date)
    TextView textViewDate;

    private MainPresenter presenter;
    private Calendar calendar;

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
        DatePickerDialog dialog = new DatePickerDialog();
        dialog.setDateSetter(this);
        dialog.show(getFragmentManager(), "createDialog");
    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        @SuppressLint("DefaultLocale")
        String date = String.format("%d:%02d:%d", dayOfMonth, month + 1, year);
        textViewDate.setText(date);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
