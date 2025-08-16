package app;

import app.utils.SqlFileExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePopulateService {

    public static void main(String[] args) {
        SqlFileExecutor executor = new SqlFileExecutor();
        try (Connection conn = Database.getInstance().getConnection()) {
            executor.execute(conn, "sql/populate_db.sql");
            System.out.println("Database populated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
