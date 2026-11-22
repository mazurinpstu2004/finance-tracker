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

        FinanceService financeService = new DefaultFinanceService();

        DefaultFileService fileService = new DefaultFileService();

        fileService.getLastIdFromFile();

        financeService.addIncome(3000, "Переводы");
        financeService.addIncome(15000, "Зарплата");
        financeService.addExpense(10000, "Электроника");

        financeService.getTransactions();



    }
}
