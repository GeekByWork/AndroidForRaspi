package com.example.techmint.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView, which;
    private int year, month, day;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dateView = (TextView) findViewById(R.id.today);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        setToday(year, month+1, day);
    }

    private void setToday(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void setFilterDate(int year, int month, int day) {
        which.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        String message = "";
        switch (view.getId())
        {
            case R.id.from:
                which = (TextView)findViewById(R.id.fromdate);
                message="Select From Date filter";
                break;
            case R.id.to:
                which = (TextView)findViewById(R.id.todate);
                message="Select To Date filter";
                break;
        }

        showDialog(999);
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int a, Bundle args) {
        if(a==999)
        {
            return new DatePickerDialog(this,
            myDateListener, year, month, day);
        }
        else
            return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    setFilterDate(arg1, arg2+1, arg3);
                }
            };
}