package Expenses.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBE {
//    private static String name = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
    private static final String DBE_URL = "jdbc:sqlite:expenses.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DBE_URL);
    }
}