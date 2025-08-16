package app.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlFileExecutor {

    public void execute(Connection conn, String path) {
        Path filePath = Paths.get(path);
        try (Statement stmt = conn.createStatement()) {
            StringBuilder sqlCommand = new StringBuilder();

            for (String line : Files.readAllLines(filePath, StandardCharsets.UTF_8)) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                sqlCommand.append(line).append(" ");

                if (line.endsWith(";")) {
                    String commandToExecute = sqlCommand.toString().replace(";", "");
                    System.out.println("Executing SQL: " + commandToExecute);
                    stmt.execute(commandToExecute);
                    sqlCommand.setLength(0);
                }
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Failed to execute SQL file: " + path, e);
        }
    }
}
