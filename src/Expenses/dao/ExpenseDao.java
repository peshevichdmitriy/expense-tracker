package Expenses.dao;

import Expenses.model.Expense;

import java.util.List;

public interface ExpenseDao {
    void create(Expense expense);
    Expense read(Long id);
    void update(Expense expense, Long id);
    void delete(Long id);
    List<Expense> getMonth();
}
