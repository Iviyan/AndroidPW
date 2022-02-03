package com.hse.androidpw;

import androidx.annotation.Nullable;

public class Period {
    public Period(String name, @Nullable String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String name;
    @Nullable
    public String teacher;
}
