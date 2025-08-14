package delete.Income.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBI {
    private static final String DBI_URL = "jdbc:sqlite:income_aug.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DBI_URL);
    }
}