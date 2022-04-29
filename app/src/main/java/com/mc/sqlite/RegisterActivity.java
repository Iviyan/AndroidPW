package com.mc.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText loginEt, passwordEt;
    CheckBox isAdminCb;
    Button regBtn;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginEt = findViewById(R.id.loginEt);
        passwordEt = findViewById(R.id.passwordEt);
        isAdminCb = findViewById(R.id.isAdminCb);
        regBtn = findViewById(R.id.regBtn);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        regBtn.setOnClickListener(view -> {
            String login = loginEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            String role = isAdminCb.isChecked() ? "admin" : "user";
            if (login.length() == 0 && password.length() == 0) {
                Toast.makeText(this, "Заполните логин и пароль", Toast.LENGTH_SHORT).show();
                return;
            }
            db.execSQL("insert into users(login, password, role) " +
                    "values ('" + loginEt.getText() + "', '" + passwordEt.getText() + "', '" + role + "')");

            Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_LONG).show();
            finish();
        });
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