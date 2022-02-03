package com.hse.androidpw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DayScheduleElementAdapter extends RecyclerView.Adapter<DayScheduleElementAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<DayScheduleElement> dayScheduleElements;

    DayScheduleElementAdapter(Context context, List<DayScheduleElement> dayScheduleElements) {
        this.dayScheduleElements = dayScheduleElements;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DayScheduleElementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.day_schedule_element_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DayScheduleElementAdapter.ViewHolder holder, int position) {
        DayScheduleElement DayScheduleElement = dayScheduleElements.get(position);
        //holder..setText(DayScheduleElement.);
    }

    @Override
    public int getItemCount() {
        return dayScheduleElements.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dayScheduleElements.get(position).denominator != null ? 2 : 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //final TextView ;
        ViewHolder(View view){
            super(view);
            // = view.findViewById(R.id.);
        }
    }
    
}
