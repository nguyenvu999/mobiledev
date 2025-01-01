package com.example.spliti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.activities.CreateGroupActivity;
import com.example.spliti.activities.NotificationActivity;
import com.example.spliti.activities.ProfileActivity;
import com.example.spliti.adapters.GroupItemAdapter;
import com.example.spliti.models.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GroupItemAdapter adapter;
    private ArrayList<Group> groupItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonProfile = findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        Button buttonNotification = findViewById(R.id.buttonNotifications);
        buttonNotification.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationActivity.class));
        });

        recyclerView = findViewById(R.id.groupdRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groupItems = new ArrayList<>();
        groupItems.add(new Group("Trip to Paris", 4, 0, "You owe 500"));
        groupItems.add(new Group("Roommates", 3, 1, "You are owed 300"));
        groupItems.add(new Group("Weekend Getaway", 6, 2, "Settled"));


        adapter = new GroupItemAdapter(this, groupItems);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAddGroup = findViewById(R.id.fabAddGroup);

        // Set click listener for FAB
        fabAddGroup.setOnClickListener(view -> {
            // Start CreateGroup activity
            Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
            startActivity(intent);
        });
    }
}