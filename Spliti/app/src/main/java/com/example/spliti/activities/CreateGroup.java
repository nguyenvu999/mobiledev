package com.example.spliti.activities;

import android.os.Bundle;
import android.widget.Button;
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

public class CreateGroup extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserItemAdapter adapter;
    private ArrayList<User> users;
    private EditText addField;
    private ImageView addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        addField = findViewById(R.id.addField);
        addButton = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.userRecyclerView);

        users = new ArrayList<>();
        adapter = new UserItemAdapter(
                this,
                users,
                position -> {
                    // Remove user from the list
                    users.remove(position);
                    adapter.notifyItemRemoved(position);
                },
                true
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String email = addField.getText().toString();
            if (!email.isEmpty()) {
                // Add a new user (for simplicity, using email as the name too)
                users.add(new User("New User", email));
                adapter.notifyItemInserted(users.size() - 1);
                addField.setText("");
            }
        });
    }
}
