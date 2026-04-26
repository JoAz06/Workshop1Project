package projects.workshop1project.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projects.workshop1project.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class StudentStore {
    private final ObservableList<StudentRecord> students = FXCollections.observableArrayList();

    public ObservableList<StudentRecord> getStudentList() {
        if(students.isEmpty()) {
            String sql = "SELECT * FROM students";

            try (Connection conn = DatabaseManager.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    students.add(new StudentRecord(
                            rs.getInt("student_id"),
                            rs.getString("fname"),
                            LocalDate.parse(rs.getString("dob")),
                            rs.getString("address"),
                            rs.getString("gender"),
                            rs.getString("degree"),
                            rs.getInt("gpa")
                    ));
                }
            } catch (SQLException e) {}
            return students;
        }else {
            return students;
        }
    }

    public boolean addStudent(StudentRecord student) {
        if (student == null) return false;

        String sql = "INSERT INTO students (fname, dob, address, gender, degree, gpa) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, student.getFname());
            ps.setString(2, student.getDob().toString());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getDegree());
            ps.setInt(6, student.getGpa());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    student.setStudentId(keys.getInt(1));
                }
            }

            students.add(student);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteStudent(StudentRecord student) {
        if (student == null) return false;

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentId());
            ps.executeUpdate();
            students.remove(student);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateStudent(StudentRecord student, String fname, LocalDate dob, String address,
                                 String gender, String degree, int gpa) {
        if (student == null) return false;

        String sql = "UPDATE students SET fname=?, dob=?, address=?, gender=?, degree=?, gpa=? WHERE student_id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fname);
            ps.setString(2, dob.toString());
            ps.setString(3, address);
            ps.setString(4, gender);
            ps.setString(5, degree);
            ps.setInt(6, gpa);
            ps.setInt(7, student.getStudentId());
            ps.executeUpdate();

            student.setFname(fname);
            student.setDob(dob);
            student.setAddress(address);
            student.setGender(gender);
            student.setDegree(degree);
            student.setGpa(gpa);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
