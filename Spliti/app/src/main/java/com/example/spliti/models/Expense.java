package com.example.spliti.models;

public class Expense {
    private String name;
    private String date;
    private int amount;
    private String paidBy;

    public Expense(String name, String date, int amount, String paidBy) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.paidBy = paidBy;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getPaidBy() {
        return paidBy;
    }
}

