package com.hse.androidpw;

import androidx.annotation.Nullable;

public class DayScheduleElement {
    public DaySchedule numerator;
    @Nullable public DaySchedule denominator;

    public DayScheduleElement(DaySchedule numerator) {
        this.numerator = numerator;
    }

    public DayScheduleElement(DaySchedule numerator, @Nullable DaySchedule denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
}
