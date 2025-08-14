package service;

import balance.Balance;
import delete.Expenses.dao.ExpenseDao;
import delete.Expenses.model.Transaction;
import delete.Expenses.model.ExpenseAccount;
import delete.Income.dao.IncomeDao;
import delete.Income.model.Income;
import delete.Income.model.IncomeAccount;
import transaction.Account;

import java.util.List;

public class BalanceService {
    Balance balanceBYN = new Balance(Account.BYN, (double) 0, 1.0);
    Balance balanceUSD = new Balance(Account.USD, (double) 0, 1.0);
    Balance balanceEUR = new Balance(Account.EUR, (double) 0, 1.0);
    Balance balanceRUB = new Balance(Account.RUB, (double) 0, 1.0);

    public Double calculateBalanceBYN(ExpenseDao expenseDao, IncomeDao incomeDao) {
        List<Transaction> expenses = expenseDao.getAll();
        List<Income> incomes = incomeDao.getAll();
        Double sumExpenses = expenses.stream()
                                     .filter(e -> e.getAccount() == ExpenseAccount.BYN)
                                     .mapToDouble(Transaction::getAmount)
                                     .sum();
        Double sumIncomes = incomes.stream()
                                   .filter(e -> e.getAccount() == IncomeAccount.BYN)
                                   .mapToDouble(Income::getAmount)
                                   .sum();
        balanceBYN.setAmount(sumIncomes-sumExpenses);
        return balanceBYN.getAmount();
    }

    public Double calculateBalanceUSD(ExpenseDao expenseDao, IncomeDao incomeDao) {
        List<Transaction> expenses = expenseDao.getAll();
        List<Income> incomes = incomeDao.getAll();
        Double sumExpenses = expenses.stream()
                .filter(e -> e.getAccount() == ExpenseAccount.USD)
                .mapToDouble(Transaction::getAmount)
                .sum();
        Double sumIncomes = incomes.stream()
                .filter(e -> e.getAccount() == IncomeAccount.USD)
                .mapToDouble(Income::getAmount)
                .sum();
        balanceUSD.setAmount(sumIncomes-sumExpenses);
        return balanceUSD.getAmount();
    }
}
