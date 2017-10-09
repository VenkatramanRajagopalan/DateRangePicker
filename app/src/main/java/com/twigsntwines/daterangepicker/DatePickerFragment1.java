package com.twigsntwines.daterangepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment1 extends Fragment implements DatePicker.OnDateChangedListener {

    public DatePickerFragment1(){}

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
        FromDatePickedListener listener = (FromDatePickedListener) getParentFragment();
        listener.OnFromDatePicked(i,i1,i2);
    }

    interface FromDatePickedListener{
        void OnFromDatePicked(int year, int month, int day);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker,container,false);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        datePicker.init(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),this);

        return view;
    }
}
