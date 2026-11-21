package ru.finance.model;

public class Transaction {

    private int id;
    private TransactionType type;
    private double amount;
    private String description;


    public static int transactionCount;

    {
        transactionCount++;
    }

    public Transaction(double amount, String description) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }

        this.id = transactionCount;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(int id, TransactionType type, double amount, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
