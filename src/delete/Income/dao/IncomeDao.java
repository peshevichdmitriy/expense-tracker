package delete.Income.dao;


import delete.Income.model.Income;

import java.util.List;

public interface IncomeDao {
    void create(Income income);
    Income read(Long id);
    void update(Income income, Long id);
    void delete(Long id);
    List<Income> getAll();
}
