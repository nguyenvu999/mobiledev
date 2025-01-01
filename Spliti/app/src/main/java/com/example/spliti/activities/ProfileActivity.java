package com.example.spliti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spliti.MainActivity;
import com.example.spliti.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        Button buttonNotification = findViewById(R.id.buttonNotifications);
        buttonNotification.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationActivity.class));
        });
    }
}