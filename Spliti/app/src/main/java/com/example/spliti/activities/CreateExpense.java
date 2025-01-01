package com.example.spliti.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.adapters.UserItemAdapter;
import com.example.spliti.fragments.SplitDialogFragment;
import com.example.spliti.models.User;

import java.util.ArrayList;

public class CreateExpense extends AppCompatActivity implements SplitDialogFragment.SplitDialogListener {
    private EditText expenseTitleInput, expenseAmountInput;
    private Spinner categorySpinner, paidBySpinner;
    private Button saveExpenseButton, equalSplitButton, customSplitButton;
    private RecyclerView splitMembersRecyclerView;
    private UserItemAdapter userAdapter;
    private ArrayList<User> members;
    private ArrayList<String> percentages;
    private TextView customSplitInstruction;

    private boolean isCustomSplit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);

        // Initialize Views
        expenseTitleInput = findViewById(R.id.expenseTitleInput);
        expenseAmountInput = findViewById(R.id.expenseAmountInput);
        categorySpinner = findViewById(R.id.expenseCategorySpinner);
        paidBySpinner = findViewById(R.id.paidBySpinner);
        saveExpenseButton = findViewById(R.id.saveExpenseButton);
        equalSplitButton = findViewById(R.id.equalSplitButton);
        customSplitButton = findViewById(R.id.customSplitButton);
        splitMembersRecyclerView = findViewById(R.id.splitMembersRecyclerView);
        customSplitInstruction = findViewById(R.id.customSplitInstruction);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        setupPaidBySpinner();
        setupCategorySpinner();

        // Set up RecyclerView
        setupSplitMembersRecyclerView();

        // Set button listeners
        equalSplitButton.setOnClickListener(v -> switchToEqualSplit());
        customSplitButton.setOnClickListener(v -> switchToCustomSplit());

        saveExpenseButton.setOnClickListener(v -> saveExpense());
    }

    private void saveExpense() {

    }

    private void setupPaidBySpinner() {
        ArrayAdapter<CharSequence> paidByAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.members_list, // Example array for members
                android.R.layout.simple_spinner_item
        );
        paidByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paidBySpinner.setAdapter(paidByAdapter);
    }

    private void setupCategorySpinner() {
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.expense_categories,
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }


    private void setupSplitMembersRecyclerView() {
        splitMembersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        members = new ArrayList<>();
        members.add(new User("You", "you@example.com"));
        members.add(new User("John Smith", "john@example.com"));

        percentages = new ArrayList<>();
        percentages.add("50%");
        percentages.add("50%");

        userAdapter = new UserItemAdapter(this, members, null, UserItemAdapter.AdapterMode.SHOW_SPLIT_MONEY);
        userAdapter.setPercentages(percentages);
        splitMembersRecyclerView.setAdapter(userAdapter);
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "NotifyDataSetChanged"})
    private void switchToEqualSplit() {
        isCustomSplit = false;

        // Change button styles
        equalSplitButton.setBackgroundTintList(getResources().getColorStateList(R.color.primary));
        equalSplitButton.setTextColor(getResources().getColor(android.R.color.white));
        customSplitButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.white));
        customSplitButton.setTextColor(getResources().getColor(R.color.gray));

        // Update percentages to equal split
        int size = members.size();
        String equalShare = 100 / size + "%";
        percentages.clear();
        for (int i = 0; i < size; i++) {
            percentages.add(equalShare);
        }

        // Update RecyclerView
        userAdapter.setPercentages(percentages);
        userAdapter.setCustomSplitEnabled(false); // Disable row clicks
        userAdapter.notifyDataSetChanged();

        // Hide the instruction text
        customSplitInstruction.setVisibility(View.GONE);
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "NotifyDataSetChanged"})
    private void switchToCustomSplit() {
        isCustomSplit = true;

        // Change button styles
        customSplitButton.setBackgroundTintList(getResources().getColorStateList(R.color.primary));
        customSplitButton.setTextColor(getResources().getColor(android.R.color.white));
        equalSplitButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.white));
        equalSplitButton.setTextColor(getResources().getColor(R.color.gray));

        // Enable custom split for RecyclerView
        userAdapter.setCustomSplitEnabled(true); // Enable row clicks
        userAdapter.notifyDataSetChanged();

        // Show the instruction text
        customSplitInstruction.setVisibility(View.VISIBLE);
    }

    public void openSplitDialog(User user, int position) {
        SplitDialogFragment dialog = SplitDialogFragment.newInstance(position, user.getName());
        dialog.show(getSupportFragmentManager(), "SplitDialog");
    }


    @Override
    public void onSplitValueChanged(int position, String value) {
        // Update the percentages list and notify the adapter
        percentages.set(position, value);
        userAdapter.setPercentages(percentages);
        userAdapter.notifyItemChanged(position);
    }

    public void onUserItemClicked(User user, int position) {
        if (isCustomSplit) {
            SplitDialogFragment dialog = SplitDialogFragment.newInstance(position, user.getName());
            dialog.show(getSupportFragmentManager(), "SplitDialog");
        }
    }

}

