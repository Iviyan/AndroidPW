package com.iv.androidpw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    CameraManager camManager;
    String cameraId = null;

    boolean flashlight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = camManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка доступа к фонарику", Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(view -> {
            try {
                camManager.setTorchMode(cameraId, flashlight = !flashlight);
                btn.setText("Flashlight - " + (flashlight ? "off" : "on"));
            } catch (CameraAccessException e) {
                e.printStackTrace();
                Toast.makeText(this, "Ошибка включения фонарика", Toast.LENGTH_SHORT).show();
            }
        });
    }
}