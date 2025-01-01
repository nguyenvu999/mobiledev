package com.example.spliti.models;

public class Notification {
    private String heading;
    private String description;
    private String timestamp;

    public Notification(String heading, String description, String timestamp) {
        this.heading = heading;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
