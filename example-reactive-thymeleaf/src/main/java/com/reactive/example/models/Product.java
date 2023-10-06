package com.reactive.example.models;

public class Product {

    private int number;
    private double amount;
    private String name;

    public Product() {
    }

    public Product(int number, double amount, String name) {
        this.number = number;
        this.amount = amount;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
