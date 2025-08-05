package Income.dao;

import java.sql.Connection;
import java.sql.Statement;

public class DBISetup {
    public static void createExpenseTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS income (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    account TEXT,
                    date TEXT,
                    amount REAL,
                    category TEXT,
                    description TEXT
                );
                """;
        try (Connection conn = DBI.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
