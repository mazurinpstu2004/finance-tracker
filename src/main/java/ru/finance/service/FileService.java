package ru.finance.service;

import ru.finance.model.Transaction;
import ru.finance.model.TransactionType;

import java.util.ArrayList;

public interface FileService {

    void saveTransactionToFile(int id, TransactionType type, double amount, String description);

    ArrayList<Transaction> loadTransactions();
}
