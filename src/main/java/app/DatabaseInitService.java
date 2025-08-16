package app;

import app.utils.SqlFileExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInitService {

    public static void main(String[] args) {
        SqlFileExecutor executor = new SqlFileExecutor();
        try (Connection conn = Database.getInstance().getConnection()) {
            executor.execute(conn, "sql/init_db.sql");
            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
