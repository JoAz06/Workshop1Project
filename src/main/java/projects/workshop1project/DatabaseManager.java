package projects.workshop1project;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:data/workshop.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        new File("data").mkdirs();

        String createPatients =
                "CREATE TABLE IF NOT EXISTS patients (" +
                "national_id TEXT PRIMARY KEY, " +
                "full_name TEXT NOT NULL, " +
                "dob TEXT NOT NULL, " +
                "gender TEXT NOT NULL, " +
                "triage TEXT NOT NULL, " +
                "phone TEXT NOT NULL, " +
                "doctor TEXT NOT NULL, " +
                "symptoms TEXT NOT NULL" +
                ")";

        String createEmployees =
                "CREATE TABLE IF NOT EXISTS employees (" +
                "emp_id INTEGER PRIMARY KEY, " +
                "emp_name TEXT NOT NULL, " +
                "emp_age INTEGER NOT NULL, " +
                "department_name TEXT NOT NULL, " +
                "emp_position TEXT NOT NULL, " +
                "salary INTEGER NOT NULL, " +
                "hire_date TEXT NOT NULL" +
                ")";

        String createStudents =
                "CREATE TABLE IF NOT EXISTS students (" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fname TEXT NOT NULL, " +
                "dob TEXT NOT NULL, " +
                "address TEXT NOT NULL, " +
                "gender TEXT NOT NULL, " +
                "degree TEXT NOT NULL, " +
                "gpa INTEGER NOT NULL" +
                ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createPatients);
            stmt.execute(createEmployees);
            stmt.execute(createStudents);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
