package service;

import dao.TransactionDao;
import delete.Expenses.dao.ExpenseDao;
import transaction.Account;
import transaction.Category;
import transaction.Transaction;
import delete.Expenses.model.ExpenseAccount;
import delete.Expenses.model.ExpenseCategory;
import delete.Income.dao.IncomeDao;
import delete.Income.model.Income;
import delete.Income.model.IncomeAccount;
import delete.Income.model.IncomeCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainService {
    public String printMainMenu(Scanner scanner) {
        System.out.println("1. Расход");
        System.out.println("2. Доход");
        System.out.println("0. Назад");

        String inner = scanner.nextLine().trim();

        return inner;
    }

    public void setTransaction(Transaction transaction, Scanner scanner){
        System.out.println("Выберите account: ");
        for (Account c : Account.values()) {
            System.out.println("- " + c.name());
        }
        transaction.setAccount(Account.valueOf(scanner.nextLine().trim()));
        System.out.println("Date: ");
        transaction.setDate(LocalDate.parse(scanner.nextLine().trim()));
        System.out.println("Amount: ");
        transaction.setAmount(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Выберите category: ");
        for (Category c : Category.values()) {
            System.out.println("- " + c.name());
        }
        transaction.setCategory(Category.valueOf(scanner.nextLine().trim()));
        System.out.println("Discription: ");
        transaction.setDescription(scanner.nextLine().trim());
        System.out.println("Транзакция: " + transaction);
    }

    public Long readTransaction(Scanner scanner, TransactionDao transactionDao){
        System.out.print("Transaction ID: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        Transaction transaction = transactionDao.read(id);
        System.out.println(transaction != null ? transaction : "Transaction not found.");
        return id;
    }

    public void printTransactionTable(List<Transaction> transactions) {
        // Заголовки
        String[] headers = {"ID","Account", "Date", "Amount", "Category", "Description"};

        // Вычислим ширины колонок — минимум по заголовку
        int wID = headers[0].length();
        int wAccount = headers[1].length();
        int wDate = headers[2].length();
        int wAmount = headers[3].length();
        int wCategory = headers[4].length();
        int wDesc = headers[5].length();

        for (Transaction e : transactions) {
            String idStr = String.valueOf(e.getID());
            wID = Math.max(wID, idStr.length());
            wAccount = Math.max(wAccount, e.getAccount().name().length());
            wDate = Math.max(wDate, e.getDate().toString().length());
            String amountStr = String.format("%.2f", e.getAmount());
            wAmount = Math.max(wAmount, amountStr.length());
            wCategory = Math.max(wCategory, e.getCategory().name().length());
            wDesc = Math.max(wDesc, safe(e.getDescription()).length());
        }

        // Форматная строка
        String format = String.format("%%-%ds | %%-%ds | %%-%ds | %%%ds | %%-%ds | %%-%ds%n",
               wID, wAccount, wDate, wAmount, wCategory, wDesc);

        // Разделитель
        String separator = String.join("-+-",
                repeat('-', wID),
                repeat('-', wAccount),
                repeat('-', wDate),
                repeat('-', wAmount),
                repeat('-', wCategory),
                repeat('-', wDesc)
        );

        // Печать
        System.out.printf(format, (Object[]) headers);
        System.out.println(separator);
        double sumAmount = 0;
        for (Transaction e : transactions) {
            sumAmount +=e.getAmount();
            String desc = safe(e.getDescription());
            if (desc.length() > wDesc) {
                desc = desc.substring(0, wDesc - 1) + "…";
            }
            System.out.printf(format,
                    e.getAccount().name(),
                    e.getDate().toString(),
                    String.format("%.2f", e.getAmount()),
                    e.getCategory().name(),
                    desc);
        }
        System.out.println(separator);
        System.out.println("Сумма за период: " +sumAmount);
    }

    // Вспомогательные
    private static String safe(String s) {
        return s == null ? "" : s.replaceAll("\\s+", " ").trim();
    }

    private static String repeat(char c, int times) {
        return String.valueOf(c).repeat(Math.max(0, times));
    }
}