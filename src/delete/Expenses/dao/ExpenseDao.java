package delete.Expenses.dao;

import delete.Expenses.model.Transaction;

import java.util.List;

public interface ExpenseDao {
    void create(Transaction expense);
    Transaction read(Long id);
    void update(Transaction expense, Long id);
    void delete(Long id);
    List<Transaction> getAll();
}
