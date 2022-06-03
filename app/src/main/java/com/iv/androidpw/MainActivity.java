package com.iv.androidpw;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    LinearLayout container;
    Button[][] btns;
    boolean[][] mines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);

        container.removeAllViews();

        int cols = 7;
        int rows = 7;

        btns = new Button[cols][rows];
        mines = new boolean[cols][rows];

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                mines[y][x] = rnd.nextBoolean();
            }
        }

        for (int y = 0; y < rows; y++) {

            LinearLayout col = new LinearLayout(this);
            col.setOrientation(LinearLayout.VERTICAL);
            col.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));

            for (int x = 0; x < cols; x++) {

                Button cellBtn = new Button(this);
                //cellBtn.setText("" + y + " " + x);
                cellBtn.setTag(new int[] {y, x});

                cellBtn.setOnClickListener(view -> {
                    int[] coords = (int[])view.getTag();
                    int cy = coords[0];
                    int cx = coords[1];

                    if (mines[cy][cx])
                        btns[cy][cx].setText("*");
                });

                btns[y][x] = cellBtn;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                col.addView(cellBtn, params);
            }

            container.addView(col);
        }
    }
}