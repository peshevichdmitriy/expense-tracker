package dao;

import transaction.Account;
import transaction.Category;
import transaction.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public void create(Transaction transaction) {
        String sql = "INSERT INTO expenses (account, date, amount, category, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaction.getAccount().name());
            stmt.setString(2, transaction.getDate().toString());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getCategory().name());
            stmt.setString(5, transaction.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction read(Long id) {
        String sql = "SELECT id, account, date, amount, category, description FROM expenses WHERE id = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setAccount(Account.valueOf(rs.getString("account")));
                    transaction.setDate(LocalDate.parse(rs.getString("date")));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setCategory(Category.valueOf(rs.getString("category")));
                    transaction.setDescription(rs.getString("description"));
                    return transaction;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Transaction transaction, Long id) {
        String sql = "UPDATE expenses SET account = ?, date = ?, amount = ?, category = ?, description = ? WHERE id = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.getAccount().name());
            stmt.setString(2, transaction.getDate().toString());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getCategory().name());
            stmt.setString(5, transaction.getDescription());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection conn = DB.connect();
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

        String sql = "SELECT id, account, date, amount, category, description FROM expenses ORDER BY date";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setID(rs.getInt("id"));
                transaction.setAccount(Account.valueOf(rs.getString("account")));
                transaction.setDate(LocalDate.parse(rs.getString("date")));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setCategory(Category.valueOf(rs.getString("category")));
                transaction.setDescription(rs.getString("description"));
                list.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return List.copyOf(list);
    }
}
