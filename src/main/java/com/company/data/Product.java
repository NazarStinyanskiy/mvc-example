package com.company.data;

public class Product {
    private int id;
    private String name;
    private int amount;
    private double cost;

    public Product(){

    }

    public Product(int id, String name, int amount, double cost) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.cost = cost;
    }

    public Product(String name, int amount, double cost) {
        this.name = name;
        this.amount = amount;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }
}