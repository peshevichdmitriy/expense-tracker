package dao;

import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {
    public static void createExpenseTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS "transaction" (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT,
                    account TEXT,
                    date TEXT,
                    amount REAL,
                    category TEXT,
                    description TEXT
                );
                """;
        try (Connection conn = DB.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
