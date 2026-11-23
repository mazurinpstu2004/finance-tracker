package ru.finance.service.impl;

import ru.finance.model.Transaction;
import ru.finance.model.TransactionType;
import ru.finance.service.FileService;
import ru.finance.service.FinanceService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DefaultFinanceService implements FinanceService {

    private final FileService fileService = new DefaultFileService();

    @Override
    public void addIncome(double amount, String description) {
        Transaction transaction = new Transaction(TransactionType.INCOME, amount, description);
        fileService.saveTransactionToFile(transaction.getId(), transaction.getType(), transaction.getAmount(), transaction.getDescription());
    }

    @Override
    public void addExpense(double amount, String description) {
        Transaction transaction = new Transaction(TransactionType.EXPENSE, amount, description);
        fileService.saveTransactionToFile(transaction.getId(), transaction.getType(), transaction.getAmount(), transaction.getDescription());
    }

    @Override
    public void getTransactions() {
        ArrayList<Transaction> transactions = fileService.loadTransactions();

        if (transactions.isEmpty()) {
            throw new IllegalStateException("Список операций пуст");
        }

        for (Transaction t : transactions) {
            System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
        }
    }

    @Override
    public void findTransactionById(int id) {
        ArrayList<Transaction> transactions = fileService.loadTransactions();

        if (transactions.isEmpty()) {
            throw new IllegalStateException("Список операций пуст");
        }

        Iterator<Transaction> iterator = transactions.iterator();

        boolean isFound = false;

        while (iterator.hasNext()) {

            Transaction t = iterator.next();

            if (t.getId() == id) {
                isFound = true;
                System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
                break;
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("Транзакции " + id + " не существует");
        }
    }

    @Override
    public void deleteTransaction(int id) {
        ArrayList<Transaction> transactions = fileService.loadTransactions();

        if (transactions.isEmpty()) {
            throw new IllegalStateException("Список операций пуст");
        }

        Iterator<Transaction> iterator = transactions.iterator();

        boolean isFound = false;

        while (iterator.hasNext()) {

            Transaction t = iterator.next();

            if (t.getId() == id) {
                isFound = true;
                iterator.remove();
            }
        }

        fileService.deleteTransactionFromFile(transactions);

        if (!isFound) {
            throw new NoSuchElementException("Транзакции " + id + " не существует");
        }
    }

    @Override
    public void getIncome() {
        ArrayList<Transaction> transactions = fileService.loadTransactions();

        if (transactions.isEmpty()) {
            throw new IllegalStateException("Список операций пуст");
        }

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.INCOME) {
                System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
            }
        }
    }

    @Override
    public void getExpense() {
        ArrayList<Transaction> transactions = fileService.loadTransactions();

        if (transactions.isEmpty()) {
            throw new IllegalStateException("Список операций пуст");
        }

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE) {
                System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
            }
        }
    }

    @Override
    public void getStatistics() {
        ArrayList<Transaction> transactions = fileService.loadTransactions();

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
