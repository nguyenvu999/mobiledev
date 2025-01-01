package com.example.spliti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spliti.R;
import com.example.spliti.models.Notification;

import java.util.ArrayList;

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.NotificationViewHolder> {

    private final Context context;
    private final ArrayList<Notification> notifications;

    public NotificationItemAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item_recycler_view, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.headingTextView.setText(notification.getHeading());
        holder.descriptionTextView.setText(notification.getDescription());
        holder.timestampTextView.setText(notification.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView headingTextView, descriptionTextView, timestampTextView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            headingTextView = itemView.findViewById(R.id.headingText);
            descriptionTextView = itemView.findViewById(R.id.descriptionText);
            timestampTextView = itemView.findViewById(R.id.timestampText);
        }
    }
}
