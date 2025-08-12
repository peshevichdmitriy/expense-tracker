package service;

import Expenses.dao.ExpenseDao;
import Expenses.model.Expense;
import Expenses.model.ExpenseAccount;
import Expenses.model.ExpenseCategory;
import Income.dao.IncomeDao;
import Income.model.Income;
import Income.model.IncomeAccount;
import Income.model.IncomeCategory;

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

    public void setExpense(Expense expense, Scanner scanner){
        System.out.println("Выберите account: ");
        for (ExpenseAccount c : ExpenseAccount.values()) {
            System.out.println("- " + c.name());
        }
        expense.setAccount(ExpenseAccount.valueOf(scanner.nextLine().trim()));
        System.out.println("Date: ");
        expense.setDate(LocalDate.parse(scanner.nextLine().trim()));
        System.out.println("Amount: ");
        expense.setAmount(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Выберите category: ");
        for (ExpenseCategory c : ExpenseCategory.values()) {
            System.out.println("- " + c.name());
        }
        expense.setCategory(ExpenseCategory.valueOf(scanner.nextLine().trim()));
        System.out.println("Discription: ");
        expense.setDescription(scanner.nextLine().trim());
        System.out.println("Расход: " + expense);
    }

    public void setIncome(Income income, Scanner scanner){
        System.out.println("Выберите account: ");
        for (IncomeAccount c : IncomeAccount.values()) {
            System.out.println("- " + c.name());
        }
        income.setAccount(IncomeAccount.valueOf(scanner.nextLine().trim()));
        System.out.println("Date: ");
        income.setDate(LocalDate.parse(scanner.nextLine().trim()));
        System.out.println("Amount: ");
        income.setAmount(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Выберите category: ");
        for (IncomeCategory c : IncomeCategory.values()) {
            System.out.println("- " + c.name());
        }
        income.setCategory(IncomeCategory.valueOf(scanner.nextLine().trim()));
        System.out.println("Discription: ");
        income.setDescription(scanner.nextLine().trim());
        System.out.println("Пополнение: " + income);
    }

    public Long readExpense(Scanner scanner, ExpenseDao expenseDao){
        System.out.print("Expense ID: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        Expense expense = expenseDao.read(id);
        System.out.println(expense != null ? expense : "Expense not found.");
        return id;
    }

    public Long readIncome(Scanner scanner, IncomeDao incomeDao){
        System.out.print("Income ID: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        Income income = incomeDao.read(id);
        System.out.println(income != null ? income : "Income not found.");
        return id;
    }

    public void printExpensesTable(List<Expense> expenses) {
        // Заголовки
        String[] headers = {"Account", "Date", "Amount", "Category", "Description"};

        // Вычислим ширины колонок — минимум по заголовку
        int wAccount = headers[0].length();
        int wDate = headers[1].length();
        int wAmount = headers[2].length();
        int wCategory = headers[3].length();
        int wDesc = headers[4].length();

        for (Expense e : expenses) {
            wAccount = Math.max(wAccount, e.getAccount().name().length());
            wDate = Math.max(wDate, e.getDate().toString().length());
            String amountStr = String.format("%.2f", e.getAmount());
            wAmount = Math.max(wAmount, amountStr.length());
            wCategory = Math.max(wCategory, e.getCategory().name().length());
            wDesc = Math.max(wDesc, safe(e.getDescription()).length());
        }

        // Форматная строка
        String format = String.format("%%-%ds | %%-%ds | %%%ds | %%-%ds | %%-%ds%n",
                wAccount, wDate, wAmount, wCategory, wDesc);

        // Разделитель
        String separator = String.join("-+-",
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
        for (Expense e : expenses) {
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

    public void printIncomesTable(List<Income> incomes) {
        // Заголовки
        String[] headers = {"Account", "Date", "Amount", "Category", "Description"};

        // Вычислим ширины колонок — минимум по заголовку
        int wAccount = headers[0].length();
        int wDate = headers[1].length();
        int wAmount = headers[2].length();
        int wCategory = headers[3].length();
        int wDesc = headers[4].length();

        for (Income e : incomes) {
            wAccount = Math.max(wAccount, e.getAccount().name().length());
            wDate = Math.max(wDate, e.getDate().toString().length());
            String amountStr = String.format("%.2f", e.getAmount());
            wAmount = Math.max(wAmount, amountStr.length());
            wCategory = Math.max(wCategory, e.getCategory().name().length());
            wDesc = Math.max(wDesc, safe(e.getDescription()).length());
        }

        // Форматная строка
        String format = String.format("%%-%ds | %%-%ds | %%%ds | %%-%ds | %%-%ds%n",
                wAccount, wDate, wAmount, wCategory, wDesc);

        // Разделитель
        String separator = String.join("-+-",
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
        for (Income e : incomes) {
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
        sumAmount = incomes.stream()
                .filter(e -> e.getAccount() == IncomeAccount.BYN)
                .mapToDouble(Income::getAmount)
                .sum();
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