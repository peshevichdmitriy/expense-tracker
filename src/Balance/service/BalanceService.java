package Balance.service;

import Balance.model.Balance;
import Balance.model.BalanceAccount;
import Expenses.dao.ExpenseDao;
import Expenses.model.Expense;
import Income.dao.IncomeDao;
import Income.model.Income;

import java.util.List;

public class BalanceService {
    Balance balanceBYN = new Balance(BalanceAccount.BYN, (double) 0, 1.0);
    Balance balanceUSD = new Balance(BalanceAccount.USD, (double) 0, 1.0);
    Balance balanceEUR = new Balance(BalanceAccount.EUR, (double) 0, 1.0);
    Balance balanceRUB = new Balance(BalanceAccount.RUB, (double) 0, 1.0);

    public Double calculateBalanceBYN(ExpenseDao expenseDao, IncomeDao incomeDao) {
        List<Expense> expenses = expenseDao.getMonth();
        List<Income> incomes = incomeDao.getMonth();
        Double sumExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        Double sumIncomes = incomes.stream().mapToDouble(Income::getAmount).sum();
        balanceBYN.setAmount(sumIncomes-sumExpenses);
        return balanceBYN.getAmount();
    }
}
