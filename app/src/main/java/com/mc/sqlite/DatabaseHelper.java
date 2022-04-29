package com.mc.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "store.db";
    private static final int SCHEMA = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT UNIQUE, " +
                "password TEXT," +
                "role TEXT);");

        db.execSQL("CREATE TABLE news (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "header TEXT, " +
                "text TEXT," +
                "date TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users;");
        db.execSQL("DROP TABLE IF EXISTS news;");
        onCreate(db);
    }
}
