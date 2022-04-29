package com.mc.sqlite;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button authBtn, regBtn;

    ActivityResultLauncher<Intent> launchLoginActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    int userId = data.getIntExtra("userId", -1);
                    // String userLogin = data.getStringExtra("userId");
                    // String userRole = data.getStringExtra("userRole");
                    Toast.makeText(this,"Успешный вход: " + userId, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, NewsActivity.class);
                    intent.putExtras(data);
                    startActivity(intent);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authBtn = findViewById(R.id.authButton);
        regBtn = findViewById(R.id.regButton);

        authBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            launchLoginActivity.launch(intent);
        });

        regBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}