package com.mc.app_tv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.mc.app_tv.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends Activity {

    ActivityMainBinding binding;
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setDateTime();

        binding.changeDateBtn.setOnClickListener(view -> new DatePickerDialog(
                this, onDateSet,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show());

        binding.changeTimeBtn.setOnClickListener(view -> new TimePickerDialog(
                this, onTimeSet,
                dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE), true)
                .show());
    }

    DatePickerDialog.OnDateSetListener onDateSet = (view, year, monthOfYear, dayOfMonth) -> {
        dateTime.set(Calendar.YEAR, year);
        dateTime.set(Calendar.MONTH, monthOfYear);
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setDateTime();
    };

    TimePickerDialog.OnTimeSetListener onTimeSet = (view, hourOfDay, minute) -> {
        dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateTime.set(Calendar.MINUTE, minute);
        setDateTime();
    };

    private void setDateTime() {
        binding.dateTimeTv.setText(DateUtils.formatDateTime(this, dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_YEAR));
    }
}