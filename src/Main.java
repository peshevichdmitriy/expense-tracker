import Expenses.dao.DBESetup;
import Expenses.dao.ExpenseDao;
import Expenses.dao.ExpenseDaoImpl;
import Expenses.model.*;
import Income.model.*;
import Income.dao.DBISetup;
import Income.dao.IncomeDao;
import Income.dao.IncomeDaoImpl;
import Balance.service.BalanceService;
import service.MainService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseDao expenseDao = new ExpenseDaoImpl();
        IncomeDao incomeDao = new IncomeDaoImpl();
        MainService mainService = new MainService();
        BalanceService balanceService = new BalanceService();

        while (true) {
            System.out.println("1. Добавить");
            System.out.println("2. Найти");
            System.out.println("3. Изменить");
            System.out.println("4. Удалить");
            System.out.println("5. Сумма за месяц");
            System.out.println("6. Мой баланс");
            System.out.println("7. Создать копилку");
            System.out.println("8. Разбить копилку");
            System.out.println("9. Сумма в копилке");
            System.out.println("0. Выход");

            switch (scanner.nextLine()) {
                case "1" -> {
                    while (true) {
                        String inner = mainService.printMainMenu(scanner);
                        if (inner.equals("0")) {
                            break; // возвращаемся в главное меню
                        }

                        switch (inner){
                            case "1" -> {
                                DBESetup.createExpenseTable();

                                Expense expense = new Expense();
                                mainService.setExpense(expense, scanner);
                                expenseDao.create(expense);
                            }
                            case "2" -> {
                                DBISetup.createExpenseTable();

                                Income income = new Income();
                                mainService.setIncome(income, scanner);
                                incomeDao.create(income);
                            }
                            default -> System.out.println("Неверный выбор, введите 1, 2 или 0.");
                        }
                    }
                }
                case "2" -> {
                    while (true) {
                        String inner = mainService.printMainMenu(scanner);
                        if (inner.equals("0")) {
                            break; // возвращаемся в главное меню
                        }

                        switch (inner){
                            case "1" -> {
                                mainService.readExpense(scanner, expenseDao);
                            }
                            case "2" -> {
                                mainService.readIncome(scanner, incomeDao);
                            }
                            default -> System.out.println("Неверный выбор, введите 1, 2 или 0.");
                        }
                    }
                }
                case "3" -> {
                    while (true) {
                        String inner = mainService.printMainMenu(scanner);
                        if (inner.equals("0")) {
                            break; // возвращаемся в главное меню
                        }

                        switch (inner){
                            case "1" -> {
                                Long id = mainService.readExpense(scanner, expenseDao);
                                Expense expense = expenseDao.read(id);
                                mainService.setExpense(expense, scanner);
                                expenseDao.update(expense, id);
                            }
                            case "2" -> {
                                Long id = mainService.readIncome(scanner, incomeDao);
                                Income income = incomeDao.read(id);
                                mainService.setIncome(income, scanner);
                                incomeDao.update(income, id);
                            }
                            default -> System.out.println("Неверный выбор, введите 1, 2 или 0.");
                        }
                    }
                }
                case "4" -> {
                    while (true) {
                        String inner = mainService.printMainMenu(scanner);
                        if (inner.equals("0")) {
                            break; // возвращаемся в главное меню
                        }

                        switch (inner){
                            case "1" -> {
                                Long id = mainService.readExpense(scanner, expenseDao);
                                expenseDao.delete(id);
                            }
                            case "2" -> {
                                Long id = mainService.readIncome(scanner, incomeDao);
                                incomeDao.delete(id);
                            }
                            default -> System.out.println("Неверный выбор, введите 1, 2 или 0.");
                        }
                    }
                }
                case "5" -> {
                    while (true) {
                        String inner = mainService.printMainMenu(scanner);
                        if (inner.equals("0")) {
                            break; // возвращаемся в главное меню
                        }

                        switch (inner){
                            case "1" -> {
                                List <Expense> expenses = expenseDao.getAll();
                                mainService.printExpensesTable(expenses);
                            }
                            case "2" -> {
                                List <Income> incomes = incomeDao.getAll();
                                mainService.printIncomesTable(incomes);
                            }
                            default -> System.out.println("Неверный выбор, введите 1, 2 или 0.");
                        }
                    }
                }
                case "6" -> {
                    System.out.println("BYN: " + balanceService.calculateBalanceBYN(expenseDao, incomeDao));
                    System.out.println("USD: " + balanceService.calculateBalanceUSD(expenseDao, incomeDao));
                }
                case "7" -> {

                }
                case "8" -> {

                }
                case "9" -> {

                }
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
