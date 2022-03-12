package com.hse.androidpw;

import android.os.Bundle;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    static final String Nakhimovsky = "Нахимовский";
    static final String Nezhinskaya = "Нежинская";

    static final DaySchedule[] Schedule = new DaySchedule[]{
            new DaySchedule(Nakhimovsky,
                    new DayScheduleElement(
                            new Period("Системное программирование", "О.В. Копылов"),
                            new Period("Технология разработки и защиты баз данных", "Г.Ю. Волкова")),
                    new DayScheduleElement(new Period("Разработка программных модулей", "А.А. Комаров")),
                    new DayScheduleElement(new Period("Технология разработки программного обеспечения", "Л.А. Соколова")),
                    new DayScheduleElement(new Period("Физическая культура", "А.В. Андрюков")),
                    null
            ),
            new DaySchedule("",
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", ""))
            ),
            new DaySchedule("",
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", "")),
                    new DayScheduleElement(new Period("Практика", ""))
            ),
            new DaySchedule(Nezhinskaya,
                    new DayScheduleElement(new Period("Иностранный язык в профессиональной деятельности", "А.Д. Завьялова, А.Ю. Дымская")),
                    new DayScheduleElement(new Period("Инструментальные средства разработки ПО", "Ю.В. Севастьянов")),
                    new DayScheduleElement(new Period("Технология разработки и защиты баз данных", "Г.Ю. Волкова")),
                    new DayScheduleElement(new Period("Разработка программных модулей", "А.Ю. Бушин")),
                    new DayScheduleElement(
                            new Period("Разработка мобильных приложений", "А.О. Лясников"),
                            EmptyPeriod.Instance)
            ),
            new DaySchedule(Nakhimovsky,
                    new DayScheduleElement(new Period("Системное программирование", "О.В. Копылов")),
                    new DayScheduleElement(
                            new Period("Технология разработки программного обеспечения", "Л.А. Соколова"),
                            new Period("Инструментальные средства разработки ПО", "Ю.В. Севастьянов")
                    ),
                    new DayScheduleElement(new Period("Разработка мобильных приложений", "А.О. Лясников")),
                    new DayScheduleElement(
                            EmptyPeriod.Instance,
                            new Period("Разработка программных модулей", "А.Ю. Бушин")
                    ),
                    null

            ),
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