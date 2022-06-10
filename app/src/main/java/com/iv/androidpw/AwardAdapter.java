package com.iv.androidpw;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AwardAdapter  extends RecyclerView.Adapter<AwardAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Award> awards;

    AwardAdapter(Context context, List<Award> awards) {
        this.awards = awards;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public AwardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AwardAdapter.ViewHolder holder, int position) {
        Award award = awards.get(position);
        holder.img.setImageResource(award.image);
        if (award.received) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.img.setColorFilter(filter);
        } else {
            holder.img.clearColorFilter();
        }
    }

    @Override
    public int getItemCount() {
        return awards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView img;
        ViewHolder(View view){
            super(view);
            img = view.findViewById(R.id.awardImg);
        }
    }
}