package com.mc.sqlite;

public class News {
    int id;
    String header;
    String text;
    String date;

    public News(int id, String header, String text, String date) {
        this.id = id;
        this.header = header;
        this.text = text;
        this.date = date;
    }
}
