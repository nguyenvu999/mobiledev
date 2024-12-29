package com.example.spliti.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.activities.GroupDetail;
import com.example.spliti.models.Group;
import com.example.spliti.utils.ColorUtils;

import java.util.ArrayList;

public class GroupItemAdapter extends RecyclerView.Adapter<GroupItemAdapter.GroupItemViewHolder> {
    Context context;
    ArrayList<Group> groupItems;

    public GroupItemAdapter(Context context, ArrayList<Group> groupItems) {
        this.context = context;
        this.groupItems = groupItems;
    }

    @NonNull
    @Override
    public GroupItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_item_recycler_view, parent, false);
        return new GroupItemViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GroupItemViewHolder holder, int position) {
        Group groupItem = groupItems.get(position);
        holder.nameTextView.setText(groupItem.getName());
        holder.memberCountTextView.setText(groupItem.getMemberCount() + " members");

        // Set status message and background based on status
        holder.status.setText(groupItem.getStatusMessage());
        switch (groupItem.getStatus()) {
            case 0: // You owe
                holder.status.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
                holder.status.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case 1: // You are owed
                holder.status.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
                holder.status.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case 2: // Settled
                holder.status.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
                holder.status.setTextColor(ContextCompat.getColor(context, android.R.color.black));
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GroupDetail.class);
            intent.putExtra("GROUP_NAME", groupItem.getName());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return groupItems.size();
    }

    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, memberCountTextView;
        Button status;

        public GroupItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.groupName);
            memberCountTextView = itemView.findViewById(R.id.groupMembersCount);
            status = itemView.findViewById(R.id.groupStatus);
        }
    }
}

