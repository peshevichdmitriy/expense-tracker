package delete.Expenses.dao;

import delete.Expenses.model.Transaction;
import delete.Expenses.model.ExpenseAccount;
import delete.Expenses.model.ExpenseCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {
    @Override
    public void create(Transaction expense) {
        String sql = "INSERT INTO expenses (account, date, amount, category, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBE.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, expense.getAccount().name());
            stmt.setString(2, expense.getDate().toString());
            stmt.setDouble(3, expense.getAmount());
            stmt.setString(4, expense.getCategory().name());
            stmt.setString(5, expense.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction read(Long id) {
        String sql = "SELECT id, account, date, amount, category, description FROM expenses WHERE id = ?";

        try (Connection conn = DBE.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Transaction expense = new Transaction();
                    expense.setAccount(ExpenseAccount.valueOf(rs.getString("account")));
                    expense.setDate(LocalDate.parse(rs.getString("date")));
                    expense.setAmount(rs.getDouble("amount"));
                    expense.setCategory(ExpenseCategory.valueOf(rs.getString("category")));
                    expense.setDescription(rs.getString("description"));
                    return expense;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Transaction expense, Long id) {
        String sql = "UPDATE expenses SET account = ?, date = ?, amount = ?, category = ?, description = ? WHERE id = ?";

        try (Connection conn = DBE.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, expense.getAccount().name());
            stmt.setString(2, expense.getDate().toString());
            stmt.setDouble(3, expense.getAmount());
            stmt.setString(4, expense.getCategory().name());
            stmt.setString(5, expense.getDescription());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection conn = DBE.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT account, date, amount, category, description FROM expenses ORDER BY date";

        try (Connection conn = DBE.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction expense = new Transaction();
                expense.setAccount(ExpenseAccount.valueOf(rs.getString("account")));
                expense.setDate(LocalDate.parse(rs.getString("date")));
                expense.setAmount(rs.getDouble("amount"));
                expense.setCategory(ExpenseCategory.valueOf(rs.getString("category")));
                expense.setDescription(rs.getString("description"));
                list.add(expense);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return List.copyOf(list);
    }
}
