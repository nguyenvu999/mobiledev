package com.example.spliti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.MainActivity;
import com.example.spliti.R;
import com.example.spliti.adapters.NotificationItemAdapter;
import com.example.spliti.models.Notification;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotificationItemAdapter adapter;
    private ArrayList<Notification> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        Button buttonProfile = findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        recyclerView = findViewById(R.id.notificationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        notifications = new ArrayList<>();
        notifications.add(new Notification("John added a new expense", "Team Lunch - $45.00", "2 hours ago"));
        notifications.add(new Notification("Payment reminder", "Office supplies due tomorrow", "5 hours ago"));

        adapter = new NotificationItemAdapter(this, notifications);
        recyclerView.setAdapter(adapter);

    }
}
