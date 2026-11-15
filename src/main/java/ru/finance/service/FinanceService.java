package ru.finance.service;

public interface FinanceService {

    void addIncome(double amount, String description);

    void addExpense(double amount, String description);

    void getTransactions();

    void findTransactionById(int id);

    void deleteTransaction(int id);

    void getIncome();

    void getExpense();

    void getStatistics();
}
