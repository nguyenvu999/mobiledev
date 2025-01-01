package com.example.spliti.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.adapters.UserItemAdapter;
import com.example.spliti.models.User;

import java.util.ArrayList;

public class CreateGroupActivity extends AppCompatActivity {

    private RecyclerView membersRecyclerView;
    private UserItemAdapter userAdapter;
    private ArrayList<User> users;
    private EditText addField;
    private ImageView addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        // Initialize UI components
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        addField = findViewById(R.id.addField);
        addButton = findViewById(R.id.addButton);
        membersRecyclerView = findViewById(R.id.userRecyclerView);

        setupMembersRecyclerView();

        // Add member button logic
        addButton.setOnClickListener(v -> {
            String email = addField.getText().toString();
            if (!email.isEmpty()) {
                // Add a new user with email as placeholder for name
                users.add(new User("New User", email));
                userAdapter.notifyItemInserted(users.size() - 1);
                addField.setText("");
            }
        });
    }

    private void setupMembersRecyclerView() {
        // Initialize members list
        users = new ArrayList<>();
        users.add(new User("Alice", "alice@example.com"));
        users.add(new User("Bob", "bob@example.com"));

        // Initialize adapter with SHOW_CLEAR_BUTTON mode
        userAdapter = new UserItemAdapter(
                this,
                users,
                position -> {
                    // Remove user on delete button click
                    users.remove(position);
                    userAdapter.notifyItemRemoved(position);
                },
                UserItemAdapter.AdapterMode.SHOW_CLEAR_BUTTON
        );

        // Setup RecyclerView
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        membersRecyclerView.setAdapter(userAdapter);
    }
}
