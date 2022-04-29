package com.mc.sqlite;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsActivity extends AppCompatActivity implements INewsEditor{

    Button addBtn;
    RecyclerView newsRv;
    LinearLayout editGroup;

    int userId;
    String userLogin, userRole;
    boolean editing;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    NewsAdapter adapter;
    ArrayList<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        addBtn = findViewById(R.id.addBtn);
        newsRv = findViewById(R.id.newsRv);
        editGroup = findViewById(R.id.editGroup);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();

        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        userLogin = extras.getString("userLogin");
        userRole = extras.getString("userRole");

        editing = userRole.equals("admin");
        editGroup.setVisibility(editing ? View.VISIBLE : View.GONE);

        newsList = getNewsList();
        adapter = new NewsAdapter(this, newsList, editing);
        newsRv.setAdapter(adapter);


        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewsEditActivity.class);
            intent.putExtra("action", "add");
            launchNewsEditActivityForAdd.launch(intent);
        });
    }

    ArrayList<News> getNewsList() {
        Cursor cursor = db.rawQuery("select id, header, text, date from news",null);
        ArrayList<News> _newsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                News news = new News(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                _newsList.add(news);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Toast.makeText(this, "Количество статей: " + _newsList.size(), Toast.LENGTH_SHORT).show();
        return _newsList;
    }

    @Override
    public void edit(int id) {
        Intent intent = new Intent(this, NewsEditActivity.class);
        News news = newsList.get(id);
        intent.putExtra("action", "edit");
        intent.putExtra("id", news.id);
        intent.putExtra("header", news.header);
        intent.putExtra("text", news.text);
        launchNewsEditActivityForEdit.launch(intent);
    }

    @Override
    public void delete(int id) {
        db.delete("news", "id = " + newsList.get(id).id, null);
        newsList.remove(id);
        adapter.notifyItemRemoved(id);
    }

    ActivityResultLauncher<Intent> launchNewsEditActivityForAdd = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() != Activity.RESULT_OK) return;

                Intent data = result.getData();
                String header = data.getStringExtra("header");
                String text = data.getStringExtra("text");

                ContentValues cv = new ContentValues();
                cv.put("header", header);
                cv.put("text", text);
                String date = getCurrentDateTime();
                cv.put("date", date);
                int id = (int)db.insert("news", null, cv);
                newsList.add(new News(id, header, text, date));
                adapter.notifyItemInserted(newsList.size() - 1);

                Toast.makeText(this, "Статья успешно добавлена", Toast.LENGTH_SHORT).show();
            });

    ActivityResultLauncher<Intent> launchNewsEditActivityForEdit = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() != Activity.RESULT_OK) return;

                Intent data = result.getData();
                int id = data.getIntExtra("id", -1);
                String header = data.getStringExtra("header");
                String text = data.getStringExtra("text");

                ContentValues cv = new ContentValues();
                cv.put("header", header);
                cv.put("text", text);
                String date = getCurrentDateTime();
                cv.put("date", date);
                db.update("news", cv, "id = " + id, null);

                int newsIndex = 0;
                for (int i = 0; i < newsList.size(); i++) {
                    if (newsList.get(i).id == id) {
                        newsIndex = i;
                    }
                }
                News news = newsList.get(newsIndex);
                news.header = header;
                news.text = text;
                news.date = date;
                adapter.notifyItemChanged(newsIndex);

                Toast.makeText(this, "Статья успешно обновлена", Toast.LENGTH_SHORT).show();
            });

    String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        Date date = Calendar.getInstance().getTime();
        return dateFormat.format(date);
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}