package com.mc.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {

    EditText loginEt, passwordEt;
    Button loginBtn, loginBiometricBtn;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEt = findViewById(R.id.loginEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);
        loginBiometricBtn = findViewById(R.id.loginBiometricBtn);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.i("ErrorAUTH", errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(LoginActivity.this, "Успешно!", Toast.LENGTH_SHORT).show();
                Intent data = new Intent();
                data.putExtra("userId", -1);
                data.putExtra("userLogin", "");
                data.putExtra("userRole", "user");
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.i("FailedAUTH", "FAIL");
            }
        });

        loginBtn.setOnClickListener(view -> {
            String login = loginEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            if (login.length() == 0) {
                Toast.makeText(this, "Заполните логин и пароль", Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor cursor = db.rawQuery(
                    "select id, login, role from users where login ='" + loginEt.getText() + "' and password = '" + passwordEt.getText() + "'",
                    null);
            if (cursor.moveToFirst()) {
                int userId = cursor.getInt(0);
                String userLogin = cursor.getString(1);
                String userRole = cursor.getString(2);
                Intent data = new Intent();
                data.putExtra("userId", userId);
                data.putExtra("userLogin", userLogin);
                data.putExtra("userRole", userRole);
                setResult(RESULT_OK, data);
                cursor.close();
                finish();
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Успешная авторизация", Toast.LENGTH_LONG).show();
            finish();
        });

        promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                .setTitle("Авторизация")
                .setSubtitle("Прислоните палец")
                .setNegativeButtonText("Отмена")
                .build();

        loginBiometricBtn.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
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