package com.hse.androidpw;

import androidx.annotation.Nullable;

public class DayScheduleElement {
    public Period numerator;
    @Nullable public Period denominator;

    public DayScheduleElement(Period numerator) {
        this.numerator = numerator;
    }

    public DayScheduleElement(Period numerator, @Nullable Period denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
}
