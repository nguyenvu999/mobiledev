package com.example.spliti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.activities.CreateExpense;
import com.example.spliti.models.User;

import java.util.ArrayList;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.UserViewHolder> {
    public enum AdapterMode { SHOW_CLEAR_BUTTON, SHOW_SPLIT_MONEY, DEFAULT }

    private Context context;
    private ArrayList<User> users;
    private OnDeleteClickListener onDeleteClickListener;
    private AdapterMode mode; // Determines behavior
    private ArrayList<String> percentages; // Split percentages
    private boolean isCustomSplitEnabled = false; // Control row clicks

    public UserItemAdapter(Context context, ArrayList<User> users, OnDeleteClickListener listener, AdapterMode mode) {
        this.context = context;
        this.users = users;
        this.onDeleteClickListener = listener;
        this.mode = mode;
        this.percentages = new ArrayList<>();
    }

    // Enable or disable row clicks based on split mode
    public void setCustomSplitEnabled(boolean enabled) {
        this.isCustomSplitEnabled = enabled;
        notifyDataSetChanged();
    }

    public void setPercentages(ArrayList<String> percentages) {
        this.percentages = percentages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item_recycler_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());

        if (mode == AdapterMode.SHOW_CLEAR_BUTTON) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.percentageTextView.setVisibility(View.GONE);
            holder.deleteButton.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(position);
                }
            });
        } else if (mode == AdapterMode.SHOW_SPLIT_MONEY) {
            holder.deleteButton.setVisibility(View.GONE);
            holder.percentageTextView.setVisibility(View.VISIBLE);
            holder.percentageTextView.setText(percentages.size() > position ? percentages.get(position) : "0%");

            // Enable row click only if custom split is active
            if (isCustomSplitEnabled) {
                holder.itemView.setOnClickListener(v -> {
                    if (context instanceof CreateExpense) {
                        ((CreateExpense) context).openSplitDialog(user, position);
                    }
                });
            } else {
                holder.itemView.setOnClickListener(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, percentageTextView;
        ImageButton deleteButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.memberName);
            emailTextView = itemView.findViewById(R.id.memberEmail);
            deleteButton = itemView.findViewById(R.id.groupStatus);
            percentageTextView = itemView.findViewById(R.id.memberPercentage);
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}

