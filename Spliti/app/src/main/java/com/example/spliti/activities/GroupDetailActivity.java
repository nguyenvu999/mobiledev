package com.example.spliti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.adapters.ExpenseItemAdapter;
import com.example.spliti.adapters.UserItemAdapter;
import com.example.spliti.models.Expense;
import com.example.spliti.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupDetailActivity extends AppCompatActivity {

    private RecyclerView expensesRecyclerView, membersRecyclerView;
    private ExpenseItemAdapter expenseAdapter;
    private UserItemAdapter userAdapter;
    private ArrayList<Expense> expenses;
    private ArrayList<User> users;
    private Button membersTabButton, expensesTabButton;
    FloatingActionButton createExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        createExpenseButton = findViewById(R.id.fabAddExpense);
        createExpenseButton.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateExpense.class));
        });

        // Retrieve the group name from the Intent
        String groupName = getIntent().getStringExtra("GROUP_NAME");

        // Set the group name to the title TextView
        TextView groupNameTitle = findViewById(R.id.groupNameTitle);
        if (groupName != null) {
            groupNameTitle.setText(groupName);
        }

        // Initialize views
        expensesRecyclerView = findViewById(R.id.expensesRecyclerView);
        membersRecyclerView = findViewById(R.id.membersRecyclerView);
        membersTabButton = findViewById(R.id.membersTabButton);
        expensesTabButton = findViewById(R.id.expensesTabButton);

        // Setup RecyclerViews
        setupExpensesRecyclerView();
        setupMembersRecyclerView();

        // Tab switching logic
        membersTabButton.setOnClickListener(v -> switchToMembersTab());
        expensesTabButton.setOnClickListener(v -> switchToExpensesTab());
    }

    private void setupExpensesRecyclerView() {
        expensesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenses = new ArrayList<>();
        expenses.add(new Expense("Dinner at Olive Garden", "Jan 15, 2025", 1200, "John"));
        expenses.add(new Expense("Taxi to Hotel", "Jan 14, 2025", 800, "Sarah"));
        expenses.add(new Expense("Museum Tickets", "Jan 14, 2025", 2400, "Mike"));
        expenseAdapter = new ExpenseItemAdapter(this, expenses);
        expensesRecyclerView.setAdapter(expenseAdapter);
    }

    private void setupMembersRecyclerView() {
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Alice", "alice@example.com"));
        users.add(new User("Bob", "bob@example.com"));
        users.add(new User("Charlie", "charlie@example.com"));

        userAdapter = new UserItemAdapter(this, users, position -> {
            users.remove(position);
            userAdapter.notifyItemRemoved(position);
        }, UserItemAdapter.AdapterMode.DEFAULT);
        membersRecyclerView.setAdapter(userAdapter);
    }


    private void switchToMembersTab() {
        // Show members and hide expenses
        membersRecyclerView.setVisibility(RecyclerView.VISIBLE);
        expensesRecyclerView.setVisibility(RecyclerView.GONE);

        // Highlight members tab
        highlightActiveTab(membersTabButton, expensesTabButton);
    }

    private void switchToExpensesTab() {
        // Show expenses and hide members
        expensesRecyclerView.setVisibility(RecyclerView.VISIBLE);
        membersRecyclerView.setVisibility(RecyclerView.GONE);

        // Highlight expenses tab
        highlightActiveTab(expensesTabButton, membersTabButton);
    }

    private void highlightActiveTab(Button activeButton, Button inactiveButton) {
        // Set active tab's text color to primary
        activeButton.setTextColor(getResources().getColor(R.color.primary));
        activeButton.setTextSize(16);
        activeButton.setTypeface(null, android.graphics.Typeface.BOLD);

        // Set inactive tab's text color to gray
        inactiveButton.setTextColor(getResources().getColor(R.color.gray));
        inactiveButton.setTextSize(16);
        inactiveButton.setTypeface(null, android.graphics.Typeface.BOLD);
    }
}
