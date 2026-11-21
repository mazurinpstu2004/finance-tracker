package ru.finance;

import ru.finance.model.Transaction;
import ru.finance.model.TransactionType;
import ru.finance.service.FileService;
import ru.finance.service.FinanceService;
import ru.finance.service.impl.DefaultFileService;
import ru.finance.service.impl.DefaultFinanceService;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {

//        FinanceService financeService = new DefaultFinanceService();
//
//        financeService.addIncome(-3000, "Переводы");
//        financeService.addIncome(15000, "Зарплата");
//        financeService.addExpense(10000, "Электроника");
//
//        System.out.println("-----------------------------");
//
//        financeService.getTransactions();
//
//        System.out.println("-----------------------------");
//
//        financeService.findTransactionById(1);
//
//        System.out.println("-----------------------------");
//
//        financeService.getIncome();
//
//        System.out.println("-----------------------------");
//
//        financeService.getExpense();
//
//        System.out.println("-----------------------------");
//
//        financeService.getStatistics();
//
//        System.out.println("-----------------------------");
//
//        financeService.deleteTransaction(2);
//
//        System.out.println("-----------------------------");
//
//        financeService.getTransactions();
//
//        System.out.println("-----------------------------");

        FileService fileService = new DefaultFileService();

        fileService.saveTransactionToFile(1, TransactionType.EXPENSE, 3, "e");
        fileService.saveTransactionToFile(2, TransactionType.INCOME, 12, "i");
        fileService.saveTransactionToFile(3, TransactionType.INCOME, 19, "i");

        ArrayList<Transaction> transactions = fileService.loadTransactions();

        for (Transaction t : transactions) {
            System.out.println(t.getId() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
        }
    }
}
