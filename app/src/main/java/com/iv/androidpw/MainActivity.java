package com.iv.androidpw;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iv.androidpw.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTime();

        binding.changeTimeBtn.setOnClickListener(view -> new TimePickerDialog(
                this, onTimeSet,
                dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE), true)
                .show());

        binding.setAlarmBtn.setOnClickListener(view -> {
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmManagerBroadcastReceiver.class);
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    // System.currentTimeMillis() + 2000,
                    dateTime.getTimeInMillis(),
                    PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0));
            Toast.makeText(this, "Будильник заведён", Toast.LENGTH_LONG).show();
        });
    }

    TimePickerDialog.OnTimeSetListener onTimeSet = (view, hourOfDay, minute) -> {
        dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateTime.set(Calendar.MINUTE, minute);
        setTime();
    };

    private void setTime() {
        binding.timeTv.setText(DateUtils.formatDateTime(this, dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
    }


}