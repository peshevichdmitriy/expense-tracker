package dao;

import transaction.Account;
import transaction.Category;
import transaction.Transaction;
import transaction.Type;

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
        String sql = "INSERT INTO \"transaction\" (account, type, date, amount, category, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaction.getAccount().name());
            stmt.setString(2, transaction.getType().name());
            stmt.setString(3, transaction.getDate().toString());
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction.getCategory().name());
            stmt.setString(6, transaction.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction read(Long id) {
        String sql = "SELECT id, type, account, date, amount, category, description FROM \"transaction\" WHERE id = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setType(Type.valueOf(rs.getString("type")));
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
        String sql = "UPDATE \"transaction\" SET type = ?, account = ?, date = ?, amount = ?, category = ?, description = ? WHERE id = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.getType().name());
            stmt.setString(2, transaction.getAccount().name());
            stmt.setString(3, transaction.getDate().toString());
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction.getCategory().name());
            stmt.setString(6, transaction.getDescription());
            stmt.setLong(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM \"transaction\" WHERE id = ?";

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

        String sql = "SELECT id, type, account, date, amount, category, description FROM \"transaction\" ORDER BY date";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setID(rs.getInt("id"));
                transaction.setType(Type.valueOf(rs.getString("type")));
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
