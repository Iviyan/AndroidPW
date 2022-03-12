package com.hse.androidpw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Item> items;

    ScheduleAdapter(Context context, List<DaySchedule> daySchedules) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        for (DaySchedule element : daySchedules) {
            items.add(new HeaderItem(element.place));
            items.add(new PeriodItem(element.first, 1));
            items.add(new PeriodItem(element.second, 2));
            items.add(new PeriodItem(element.third, 3));
            items.add(new PeriodItem(element.fourth, 4));
            items.add(new PeriodItem(element.fifth, 5));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.Header)
            return new HeaderViewHolder(inflater.inflate(R.layout.day_schedule_title_list_item, parent, false));
        if (viewType == ItemType.EmptyPeriod)
            return new EmptyPeriodViewHolder(inflater.inflate(R.layout.empty_day_schedule_element_list_item, parent, false));
        if (viewType == ItemType.SimplePeriod)
            return new SimplePeriodViewHolder(inflater.inflate(R.layout.day_schedule_element_list_item, parent, false));
        if (viewType == ItemType.TogglePeriod)
            return new TogglePeriodViewHolder(inflater.inflate(R.layout.toggle_day_schedule_element_list_item, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderItem item = (HeaderItem) items.get(position);
            HeaderViewHolder typedHolder = (HeaderViewHolder) holder;
            typedHolder.placeTextView.setText(item.place);
        } else if (holder instanceof SimplePeriodViewHolder) {
            PeriodItem item = (PeriodItem) items.get(position);
            Period period = (Period)item.dayScheduleElement.numerator;
            SimplePeriodViewHolder typedHolder = (SimplePeriodViewHolder) holder;
            typedHolder.nameTextView.setText(period.name);
            typedHolder.teacherTextView.setText(period.teacher);
            //if (period.teacher.isEmpty()) typedHolder.teacherTextView.setVisibility(View.GONE);
            typedHolder.numberTextView.setText(String.valueOf(item.number));
        } else if (holder instanceof TogglePeriodViewHolder) {
            PeriodItem item = (PeriodItem) items.get(position);
            TogglePeriodViewHolder typedHolder = (TogglePeriodViewHolder) holder;
            if (item.dayScheduleElement.numerator instanceof Period) {
                Period period = (Period)item.dayScheduleElement.numerator;
                typedHolder.name1TextView.setText(period.name);
                typedHolder.teacher1TextView.setText(period.teacher);
            }
            if (item.dayScheduleElement.denominator instanceof Period) {
                Period period = (Period)item.dayScheduleElement.denominator;
                typedHolder.name2TextView.setText(period.name);
                typedHolder.teacher2TextView.setText(period.teacher);
            }
            typedHolder.numberTextView.setText(String.valueOf(item.number));
        } else if (holder instanceof EmptyPeriodViewHolder) {
            PeriodItem item = (PeriodItem) items.get(position);
            EmptyPeriodViewHolder typedHolder = (EmptyPeriodViewHolder) holder;
            typedHolder.numberTextView.setText(String.valueOf(item.number));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Item item = items.get(position);
        if (item instanceof HeaderItem) return ItemType.Header;
        PeriodItem periodItem = ((PeriodItem) item);
        if (periodItem.dayScheduleElement == null) return ItemType.EmptyPeriod;
        return periodItem.dayScheduleElement.denominator == null ? ItemType.SimplePeriod : ItemType.TogglePeriod;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        final TextView placeTextView;

        HeaderViewHolder(View view) {
            super(view);
            placeTextView = view.findViewById(R.id.placeTextView);
        }
    }

    public static class EmptyPeriodViewHolder extends RecyclerView.ViewHolder {
        final TextView numberTextView;

        EmptyPeriodViewHolder(View view) {
            super(view);
            numberTextView = view.findViewById(R.id.numberTextView);
        }
    }

    public static class SimplePeriodViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTextView, teacherTextView,
                numberTextView;

        SimplePeriodViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
            teacherTextView = view.findViewById(R.id.teacherTextView);
            numberTextView = view.findViewById(R.id.numberTextView);
        }
    }

    public static class TogglePeriodViewHolder extends RecyclerView.ViewHolder {
        final TextView name1TextView, teacher1TextView,
                name2TextView, teacher2TextView,
                numberTextView;

        TogglePeriodViewHolder(View view) {
            super(view);
            name1TextView = view.findViewById(R.id.name1TextView);
            teacher1TextView = view.findViewById(R.id.teacher1TextView);
            name2TextView = view.findViewById(R.id.name2TextView);
            teacher2TextView = view.findViewById(R.id.teacher2TextView);
            numberTextView = view.findViewById(R.id.numberTextView);
        }
    }

    static class ItemType {
        public static final int Header = 1;
        public static final int EmptyPeriod = 2;
        public static final int SimplePeriod = 3;
        public static final int TogglePeriod = 4;

    }

    class Item {}

    class HeaderItem extends Item {
        public HeaderItem(String place) {
            this.place = place;
        }

        String place;
    }

    class PeriodItem extends Item {
        DayScheduleElement dayScheduleElement;
        int number;

        public PeriodItem(DayScheduleElement dayScheduleElement, int number) {
            this.dayScheduleElement = dayScheduleElement;
            this.number = number;
        }
    }

}
