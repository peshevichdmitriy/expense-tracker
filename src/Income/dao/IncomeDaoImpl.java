package Income.dao;

import Expenses.dao.DBE;
import Income.model.Income;
import Income.model.IncomeAccount;
import Income.model.IncomeCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeDaoImpl implements IncomeDao{
    @Override
    public void create(Income income) {
        String sql = "INSERT INTO income (account, date, amount, category, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBI.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, income.getAccount().name());
            stmt.setString(2, income.getDate().toString());
            stmt.setDouble(3, income.getAmount());
            stmt.setString(4, income.getCategory().name());
            stmt.setString(5, income.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Income read(Long id) {
        String sql = "SELECT id, account, date, amount, category, description FROM income WHERE id = ?";

        try (Connection conn = DBI.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Income income = new Income();
                    income.setAccount(IncomeAccount.valueOf(rs.getString("account")));
                    income.setDate(LocalDate.parse(rs.getString("date")));
                    income.setAmount(rs.getDouble("amount"));
                    income.setCategory(IncomeCategory.valueOf(rs.getString("category")));
                    income.setDescription(rs.getString("description"));
                    return income;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Income income, Long id) {
        String sql = "UPDATE income SET account = ?, date = ?, amount = ?, category = ?, description = ? WHERE id = ?";

        try (Connection conn = DBI.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, income.getAccount().name());
            stmt.setString(2, income.getDate().toString());
            stmt.setDouble(3, income.getAmount());
            stmt.setString(4, income.getCategory().name());
            stmt.setString(5, income.getDescription());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM income WHERE id = ?";

        try (Connection conn = DBI.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Income> getMonth() {
        List<Income> list = new ArrayList<>();

        String sql = "SELECT account, date, amount, category, description FROM income ORDER BY date";

        try (Connection conn = DBI.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Income income = new Income();
                income.setAccount(IncomeAccount.valueOf(rs.getString("account")));
                income.setDate(LocalDate.parse(rs.getString("date")));
                income.setAmount(rs.getDouble("amount"));
                income.setCategory(IncomeCategory.valueOf(rs.getString("category")));
                income.setDescription(rs.getString("description"));
                list.add(income);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return List.copyOf(list);
    }
}
