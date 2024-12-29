package com.example.spliti.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.models.Expense;

import java.util.ArrayList;

public class ExpenseItemAdapter extends RecyclerView.Adapter<ExpenseItemAdapter.ExpenseViewHolder> {

    private final Context context;
    private final ArrayList<Expense> expenses;

    public ExpenseItemAdapter(Context context, ArrayList<Expense> expenses) {
        this.context = context;
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.expense_item_recycler_view, parent, false);
        return new ExpenseViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.nameTextView.setText(expense.getName());
        holder.dateTextView.setText(expense.getDate());
        holder.amountTextView.setText(String.format("%,d $", expense.getAmount()));
        holder.paidByTextView.setText(String.format("Paid by %s", expense.getPaidBy()));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, dateTextView, amountTextView, paidByTextView;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.expenseName);
            dateTextView = itemView.findViewById(R.id.expenseDate);
            amountTextView = itemView.findViewById(R.id.expenseAmount);
            paidByTextView = itemView.findViewById(R.id.expensePaidUser);
        }
    }
}

