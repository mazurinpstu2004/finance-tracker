package ru.finance.service.impl;

import ru.finance.model.Transaction;
import ru.finance.model.TransactionType;
import ru.finance.service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultFileService implements FileService {

    private static final String PATH = "data/transactions.txt";

    @Override
    public void saveTransactionToFile(int id, TransactionType type, double amount, String description) {
        Path path = Path.of(PATH);

        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, id + "|" + type + "|" + amount + "|" + description + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Transaction> loadTransactions() {
        Path path = Path.of(PATH);

        ArrayList<Transaction> transactions = new ArrayList<>();

        if (Files.notExists(path)) {
            return transactions;
        }

        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (String l : lines) {
            String[] params = l.split("\\|");

            if (params.length != 4) {
                throw new IllegalStateException("Размер параметров строки " + l + "не соответствует заявленной длине");
            }

            int id = Integer.parseInt(params[0]);

            TransactionType type = TransactionType.valueOf(params[1]);

            double amount = Double.parseDouble(params[2]);

            String description = params[3];

            Transaction transaction = new Transaction(id, type, amount, description);

            transactions.add(transaction);
        }

        return transactions;
    }

    @Override
    public void deleteTransactionFromFile(ArrayList<Transaction> transactions) {
        Path path = Path.of(PATH);

        try {
            Files.createDirectories(path.getParent());
            StringBuilder sb = new StringBuilder();

            for (Transaction t : transactions) {
                sb.append(t.getId() + "|" + t.getType() + "|" + t.getAmount() + "|" + t.getDescription() + "\n");
            }

            Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getLastIdFromFile() {
        Path path = Path.of(PATH);

        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        if (lines.isEmpty()) {
            Transaction.transactionCount = 0;
            return;
        }

        String lastLine = lines.getLast();
        String[] params = lastLine.split("\\|");

        Transaction.transactionCount = Integer.parseInt(params[0]);
    }
}
