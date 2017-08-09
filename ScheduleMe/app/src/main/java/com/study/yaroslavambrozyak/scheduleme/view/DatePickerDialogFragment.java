package com.study.yaroslavambrozyak.scheduleme.view;


import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.view.interfaces.DateSetter;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements OnDateSetListener {

    private DateSetter dateSetter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new DatePickerDialog(getActivity(),R.style.PickerDialogTheme, this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = App.getApp().getCalendar();
        calendar.set(year,month,dayOfMonth);
        dateSetter.setDate(year,month,dayOfMonth);
    }

    public void setDateSetter(DateSetter dateSetter){
        this.dateSetter = dateSetter;
    }
}
