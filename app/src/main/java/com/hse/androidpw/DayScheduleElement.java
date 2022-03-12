package com.hse.androidpw;

import androidx.annotation.Nullable;

public class DayScheduleElement {
    public PeriodBase numerator;
    @Nullable public PeriodBase denominator;

    public DayScheduleElement(PeriodBase numerator) {
        this.numerator = numerator;
    }

    public DayScheduleElement(PeriodBase numerator, @Nullable PeriodBase denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
}
