package dao;

import transaction.Transaction;

import java.util.List;

public interface TransactionDao {
    void create(Transaction transaction);
    Transaction read(Long id);
    void update(Transaction expense, Long id);
    void delete(Long id);
    List<Transaction> getAll();
}
