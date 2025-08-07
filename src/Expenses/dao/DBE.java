package Expenses.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBE {

    private static final String DBE_URL = "jdbc:sqlite:expenses_aug.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DBE_URL);
    }
}