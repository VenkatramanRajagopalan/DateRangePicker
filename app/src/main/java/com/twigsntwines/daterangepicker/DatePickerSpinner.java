package com.twigsntwines.daterangepicker;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerSpinner extends LinearLayout {

    private static final String TAG = DatePickerSpinner.class.getSimpleName();
    TextView spinnerText;
    Spinner dateSpinner;

    private Calendar cal;

    private AppCompatActivity activity;


    private DateRangePickedListener listener;

    /**Constructor**/
    public DatePickerSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        activity = (AppCompatActivity) context;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.DatePickerSpinner);
        String textViewString = typedArray.getString(R.styleable.DatePickerSpinner_spinnerText);
        typedArray.recycle();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(8,9,8,8);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            layoutInflater.inflate(R.layout.date_picker_spinner,this,true);
            spinnerText = (TextView) getChildAt(0);
            spinnerText.setText(textViewString);
            spinnerText.setEllipsize(TextUtils.TruncateAt.END);
            spinnerText.setText(R.string.spinnerText);
            spinnerText.setPadding(8,8,8,8);
            spinnerText.setTextColor(R.color.black);
            spinnerText.setGravity(Gravity.START|Gravity.CENTER);
            spinnerText.setMaxLines(1);
            dateSpinner = (Spinner) getChildAt(1);
            initializeSpinner();
        }

        cal = Calendar.getInstance();
    }

    /**Set Listener for DateRangePicked Interface**/
    public void setDateRangePickedListener(DateRangePickedListener listener){
        this.listener = listener;
    }

    /**Set Text for spinnerText programmatically**/
    public void setSpinnerText(String text){
        spinnerText.setText(text);
    }

    /**Set Visibility for spinnerText programmatically**/
    public void setSpinnerTextVisibility(boolean visibility){
        spinnerText.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    /**Returns Text assigned to spinnerText**/
    public String getSpinnerText(){
        return spinnerText.getText().toString();
    }

    /**Returns spinnerText**/
    public TextView getSpinnerTextView(){
        return spinnerText;
    }

    /**Initializes spinner adapter and list values**/
    private void initializeSpinner(){
        final String[] dateSpinnerArray = new String[]{"Today","This Week","This Month","Custom"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,dateSpinnerArray);
        dateSpinner.setAdapter(adapter);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = dateSpinnerArray[i];
                Log.e(TAG,"Selected Item -> "+selectedItem);
                if(!selectedItem.equals("Custom")){
                    if(listener != null)
                        listener.OnDateRangePicked(getFromDate(selectedItem),getToDate());
                } else {
                    android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
                    datePickerDialog.setOnDateRangePickedListener((DateRangePickedListener) listener);
                    datePickerDialog.show(fragmentManager,"Date Picker");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**Returns Calendar object for FromDate based on selectedItem**/
    private Calendar getFromDate(String selectedItem){
        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        switch (selectedItem){
            case "Today":
                Log.e(TAG,cal.getTime().toString()+" Today");
                break;

            case "This Week":
                cal.set(Calendar.DAY_OF_WEEK,1);
                break;

            case "This Month":
                cal.set(Calendar.DAY_OF_MONTH,1);
                //Log.e(TAG,"Actual Size ->" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                break;
        }

        return cal;
    }

    /**Returns Calendar object for ToDate**/
    private Calendar getToDate(){
        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal;
    }
}
