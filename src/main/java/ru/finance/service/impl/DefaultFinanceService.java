package ru.finance.service.impl;

import ru.finance.model.Transaction;
import ru.finance.model.TransactionType;
import ru.finance.service.FinanceService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DefaultFinanceService implements FinanceService {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    @Override
    public void addIncome(double amount, String description) {
        Transaction transaction = new Transaction(amount, description);
        transaction.setType(TransactionType.INCOME);
        transactions.add(transaction);
    }

    @Override
    public void addExpense(double amount, String description) {
        Transaction transaction = new Transaction(amount, description);
        transaction.setType(TransactionType.EXPENSE);
        transactions.add(transaction);
    }

    @Override
    public void getTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
        }
    }

    @Override
    public void findTransactionById(int id) {
        Iterator<Transaction> iterator = transactions.iterator();

        boolean isFound = false;

        while (iterator.hasNext()) {

            Transaction t = iterator.next();

            if (t.getId() == id) {
                isFound = true;
                System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("Транзакции " + id + " не существует");
        }
    }

    @Override
    public void deleteTransaction(int id) {
        Iterator<Transaction> iterator = transactions.iterator();

        boolean isFound = false;

        while (iterator.hasNext()) {

            Transaction t = iterator.next();

            if (t.getId() == id) {
                isFound = true;
                iterator.remove();
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("Транзакции " + id + " не существует");
        }
    }

    @Override
    public void getIncome() {
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.INCOME) {
                System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
            }
        }
    }

    @Override
    public void getExpense() {
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE) {
                System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
            }
        }
    }

    @Override
    public void getStatistics() {
        double income = 0;
        double expense = 0;

        int incomeCount = 0;
        int expenseCount = 0;

        for (Transaction t : transactions) {

            if (t.getType() == TransactionType.INCOME) {
                income += t.getAmount();
                incomeCount++;
            }

            if (t.getType() == TransactionType.EXPENSE) {
                expense += t.getAmount();
                expenseCount++;
            }
        }

        double balance = income - expense;

        System.out.println("Общий доход: " + income
                + "\nОбщие расходы: " + expense
                + "\nТекущий баланс: " + balance
                + "\nКоличество доходов: " + incomeCount
                + "\nКоличество расходов: " + expenseCount
                + "\nВсего операций: " + transactions.size());
    }
}
