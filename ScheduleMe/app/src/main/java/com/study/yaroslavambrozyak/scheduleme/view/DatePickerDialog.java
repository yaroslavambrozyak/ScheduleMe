package com.study.yaroslavambrozyak.scheduleme.view;


import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.study.yaroslavambrozyak.scheduleme.view.interfaces.DateSetter;

import java.util.Calendar;

public class DatePickerDialog extends DialogFragment implements OnDateSetListener {

    private DateSetter dateSetter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new android.app.DatePickerDialog(getActivity(), this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,dayOfMonth);
        dateSetter.setDate(year,month,dayOfMonth);
    }

    public void setDateSetter(DateSetter dateSetter){
        this.dateSetter = dateSetter;
    }
}
