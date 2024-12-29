package com.example.spliti.models;

public class Group {
    private String name;
    private int memberCount;
    private int status;
    private String statusMessage;

    public Group(String name, int memberCount, int status, String statusMessage) {
        this.name = name;
        this.memberCount = memberCount;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
