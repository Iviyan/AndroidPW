package com.iv.androidpw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    ArrayList<Award> awards = new ArrayList<>(Arrays.asList(
        new Award(R.mipmap.ic_launcher),
        new Award(R.mipmap.ic_launcher),
        new Award(R.mipmap.ic_launcher),
        new Award(R.mipmap.ic_launcher)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = 0; i < awards.size(); i++) {
            awards.get(i).received = rnd.nextBoolean();
        }

        RecyclerView awardsRv = findViewById(R.id.awardsRv);

        AwardAdapter awardAdapter = new AwardAdapter(this, awards);
        awardsRv.setAdapter(awardAdapter);
    }
}