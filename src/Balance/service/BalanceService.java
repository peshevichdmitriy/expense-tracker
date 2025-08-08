package Balance.service;

import Balance.model.Balance;
import Balance.model.BalanceAccount;
import Expenses.dao.ExpenseDao;
import Expenses.model.Expense;
import Expenses.model.ExpenseAccount;
import Income.dao.IncomeDao;
import Income.model.Income;
import Income.model.IncomeAccount;

import java.util.List;

public class BalanceService {
    Balance balanceBYN = new Balance(BalanceAccount.BYN, (double) 0, 1.0);
    Balance balanceUSD = new Balance(BalanceAccount.USD, (double) 0, 1.0);
    Balance balanceEUR = new Balance(BalanceAccount.EUR, (double) 0, 1.0);
    Balance balanceRUB = new Balance(BalanceAccount.RUB, (double) 0, 1.0);

    public Double calculateBalanceBYN(ExpenseDao expenseDao, IncomeDao incomeDao) {
        List<Expense> expenses = expenseDao.getAll();
        List<Income> incomes = incomeDao.getAll();
        Double sumExpenses = expenses.stream()
                                     .filter(e -> e.getAccount() == ExpenseAccount.BYN)
                                     .mapToDouble(Expense::getAmount)
                                     .sum();
        Double sumIncomes = incomes.stream()
                                   .filter(e -> e.getAccount() == IncomeAccount.BYN)
                                   .mapToDouble(Income::getAmount)
                                   .sum();
        balanceBYN.setAmount(sumIncomes-sumExpenses);
        return balanceBYN.getAmount();
    }

    public Double calculateBalanceUSD(ExpenseDao expenseDao, IncomeDao incomeDao) {
        List<Expense> expenses = expenseDao.getAll();
        List<Income> incomes = incomeDao.getAll();
        Double sumExpenses = expenses.stream()
                .filter(e -> e.getAccount() == ExpenseAccount.USD)
                .mapToDouble(Expense::getAmount)
                .sum();
        Double sumIncomes = incomes.stream()
                .filter(e -> e.getAccount() == IncomeAccount.USD)
                .mapToDouble(Income::getAmount)
                .sum();
        balanceUSD.setAmount(sumIncomes-sumExpenses);
        return balanceUSD.getAmount();
    }
}
