package ru.finance.model;

public class Transaction {

    private final int id;
    private final TransactionType type;
    private final double amount;
    private final String description;


    public static int transactionCount;

    public Transaction(TransactionType type, double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }

        if (type == null) {
            throw new IllegalArgumentException("Тип операции не должен быть null");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Описание операции не должно быть пустым");
        }


        this.id = ++transactionCount;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(int id, TransactionType type, double amount, String description) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть положительным");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }

        if (type == null) {
            throw new IllegalArgumentException("Тип операции не должен быть null");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Описание операции не должно быть пустым");
        }

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
}
