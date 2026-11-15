package ru.finance;

import ru.finance.model.Transaction;
import ru.finance.service.FinanceService;
import ru.finance.service.impl.DefaultFinanceService;

public class Application {
    public static void main(String[] args) {

        FinanceService financeService = new DefaultFinanceService();

        financeService.addIncome(-3000, "Переводы");
        financeService.addIncome(15000, "Зарплата");
        financeService.addExpense(10000, "Электроника");

        System.out.println("-----------------------------");

        financeService.getTransactions();

        System.out.println("-----------------------------");

        financeService.findTransactionById(1);

        System.out.println("-----------------------------");

        financeService.getIncome();

        System.out.println("-----------------------------");

        financeService.getExpense();

        System.out.println("-----------------------------");

        financeService.getStatistics();

        System.out.println("-----------------------------");

        financeService.deleteTransaction(2);

        System.out.println("-----------------------------");

        financeService.getTransactions();

        System.out.println("-----------------------------");
    }
}
