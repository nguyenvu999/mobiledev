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
import com.example.spliti.models.User;

import java.util.ArrayList;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.UserViewHolder> {
    private Context context;
    private ArrayList<User> users;
    private OnDeleteClickListener onDeleteClickListener;
    private boolean showClearButton;

    public UserItemAdapter(Context context, ArrayList<User> users, OnDeleteClickListener listener, boolean showClearButton) {
        this.context = context;
        this.users = users;
        this.onDeleteClickListener = listener;
        this.showClearButton = showClearButton;
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

        if (showClearButton) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(position);
                }
            });
        } else {
            holder.deleteButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView;
        ImageButton deleteButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.memberName);
            emailTextView = itemView.findViewById(R.id.memberEmail);
            deleteButton = itemView.findViewById(R.id.groupStatus);
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}
