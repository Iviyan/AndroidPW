package com.mc.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewsEditActivity extends AppCompatActivity {

    EditText headerEt, textEt;
    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_edit);

        headerEt = findViewById(R.id.headerEt);
        textEt = findViewById(R.id.textEt);
        confirmBtn = findViewById(R.id.confirmBtn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String action = extras.getString("action", "");
            confirmBtn.setText(action.equals("edit") ? "Изменить" : "Добавить");
            headerEt.setText(extras.getString("header", ""));
            textEt.setText(extras.getString("text", ""));
        }

        confirmBtn.setOnClickListener(view -> {
            String header = headerEt.getText().toString().trim();
            String text = textEt.getText().toString().trim();
            if (header.length() == 0) {
                Toast.makeText(this, "Заполните заголовок", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent data = new Intent();
            data.putExtra("header", header);
            data.putExtra("text", text);
            int id;
            if ((id = extras.getInt("id", -1)) != -1)
                data.putExtra("id", id);
            setResult(RESULT_OK, data);
            finish();
        });
    }
}