package com.hse.androidpw;

import androidx.annotation.Nullable;

public class DaySchedule {
    public String place;
    @Nullable public DayScheduleElement first;
    @Nullable public DayScheduleElement second;
    @Nullable public DayScheduleElement third;
    @Nullable public DayScheduleElement fourth;
    @Nullable public DayScheduleElement fifth;

    public DaySchedule(
            String place, @Nullable DayScheduleElement first,
            @Nullable DayScheduleElement second,
            @Nullable DayScheduleElement third,
            @Nullable DayScheduleElement fourth,
            @Nullable DayScheduleElement fifth)
    {
        this.place = place;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }
}
