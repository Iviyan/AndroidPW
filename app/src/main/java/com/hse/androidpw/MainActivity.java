package com.hse.androidpw;

import android.os.Bundle;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    static final String Nahim = "Нахимовский";

    static final DaySchedule[] Schedule = new DaySchedule[] {
            new DaySchedule(Nahim,
                    new DayScheduleElement(new Period("Физическая культура", "А.В. Андрюков")),
                    new DayScheduleElement(
                            new Period("Физическая культура", "А.В. Андрюков"),
                            new Period("Физическая культура", "А.В. Андрюков")),
                    new DayScheduleElement(new Period("Физическая культура", "А.В. Андрюков")),
                    null,
                    null
                    ),
            new DaySchedule(Nahim,
                    new DayScheduleElement(new Period("Физическая культура", "А.В. Андрюков")),
                    new DayScheduleElement(
                            new Period("Физическая культура", "А.В. Андрюков"),
                            new Period("Физическая культура", "А.В. Андрюков")),
                    new DayScheduleElement(new Period("Физическая культура", "А.В. Андрюков")),
                    null,
                    null
                    )
    };

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, Arrays.asList(Schedule));
        recyclerView.setAdapter(scheduleAdapter);
    }
}