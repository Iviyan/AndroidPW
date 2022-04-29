package com.mc.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<News> newsList;
    private final boolean editing;
    Context context;
    private INewsEditor newsEditor;

    NewsAdapter(Context context, List<News> newsList, boolean editing) {
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
        this.editing = editing;
        this.context = context;
        this.newsEditor = (INewsEditor) context;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.headerTv.setText(news.header);
        holder.textTv.setText(news.text);
        holder.dateTv.setText(news.date);

        if (editing) {
            holder.delBtn.setOnClickListener(view -> {
                newsEditor.delete(holder.getAdapterPosition());
            });

            holder.editBtn.setOnClickListener(view -> {
                newsEditor.edit(holder.getAdapterPosition());
            });
        } else {
            holder.delBtn.setVisibility(View.GONE);
            holder.editBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView headerTv, textTv, dateTv;
        final Button delBtn, editBtn;

        ViewHolder(View view) {
            super(view);
            headerTv = view.findViewById(R.id.headerTv);
            textTv = view.findViewById(R.id.textTv);
            dateTv = view.findViewById(R.id.dateTv);
            delBtn = view.findViewById(R.id.delBtn);
            editBtn = view.findViewById(R.id.editBtn);
        }
    }
}