package app;

import app.model.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private <T> List<T> executeQuery(String sqlFile, ResultSetMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        try (Statement stmt = Database.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(readSqlFile(sqlFile))) {

            while (rs.next()) {
                result.add(mapper.map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query: " + sqlFile, e);
        }
        return result;
    }

    private String readSqlFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read SQL file: " + path, e);
        }
    }

    public List<ProjectPrice> printProjectPrices() {
        return executeQuery("sql/print_project_prices.sql",
                rs -> new ProjectPrice(
                        rs.getString("PROJECT_NAME"),
                        rs.getLong("PRICE")
                ));
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        return executeQuery("sql/find_youngest_eldest_workers.sql",
                rs -> new YoungestEldestWorker(
                    rs.getString("NAME"),
                    rs.getDate("BIRTHDAY").toLocalDate()
                ));
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        return executeQuery("sql/find_max_salary_worker.sql",
                rs -> new MaxSalaryWorker(
                        rs.getString("NAME"),
                        rs.getInt("SALARY")
                ));
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        return executeQuery("sql/find_max_projects_client.sql",
                rs -> new MaxProjectCountClient(
                        rs.getString("NAME"),
                        rs.getInt("PROJECT_COUNT")
                ));
    }

    public List<LongestProject> findLongestProject() {
        return executeQuery("sql/find_longest_project.sql",
                rs -> new LongestProject(
                        rs.getInt("PROJECT_ID"),
                        rs.getInt("DURATION_MONTHS")
                ));
    }
}
