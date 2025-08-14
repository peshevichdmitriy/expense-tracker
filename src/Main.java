import dao.DBSetup;
import dao.TransactionDao;
import dao.TransactionDaoImpl;
import transaction.*;
import delete.Income.model.*;
import delete.Income.dao.DBISetup;

import service.BalanceService;
import service.MainService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionDao transactionDao = new TransactionDaoImpl();
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
                                DBSetup.createExpenseTable();

                                Transaction transaction = new Transaction();
                                mainService.setTransaction(transaction, scanner);
                                transactionDao.create(transaction);
                            }
                            case "2" -> {
                                DBSetup.createExpenseTable();

                                Transaction transaction = new Transaction();
                                mainService.setTransaction(transaction, scanner);
                                transactionDao.create(transaction);
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
                                mainService.readTransaction(scanner, transactionDao);
                            }
                            case "2" -> {
                                mainService.readTransaction(scanner, transactionDao);
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
                                Long id = mainService.readTransaction(scanner, transactionDao);
                                Transaction transaction = transactionDao.read(id);
                                mainService.setTransaction(transaction, scanner);
                                transactionDao.update(transaction, id);
                            }
                            case "2" -> {
                                Long id = mainService.readTransaction(scanner, transactionDao);
                                Transaction transaction = transactionDao.read(id);
                                mainService.setTransaction(transaction, scanner);
                                transactionDao.update(transaction, id);
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
                                Long id = mainService.readTransaction(scanner, transactionDao);
                                transactionDao.delete(id);
                            }
                            case "2" -> {
                                Long id = mainService.readTransaction(scanner, transactionDao);
                                transactionDao.delete(id);
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
                                List <Transaction> transactions = transactionDao.getAll();
                                mainService.printTransactionTable(transactions);
                            }
                            case "2" -> {
                                List <Transaction> transactions = transactionDao.getAll();
                                mainService.printTransactionTable(transactions);
                            }
                            default -> System.out.println("Неверный выбор, введите 1, 2 или 0.");
                        }
                    }
                }
                case "6" -> {
                    System.out.println("BYN: " + balanceService.calculateBalanceBYN(transactionDao));
                    System.out.println("USD: " + balanceService.calculateBalanceUSD(transactionDao));
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
