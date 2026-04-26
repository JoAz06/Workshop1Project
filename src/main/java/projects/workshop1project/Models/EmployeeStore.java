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

public class EmployeeStore {
    private final ObservableList<EmployeeRecord> employees = FXCollections.observableArrayList();

    public ObservableList<EmployeeRecord> getEmployeesList() {
        if(employees.isEmpty()) {
            String sql = "SELECT * FROM employees";

            try (Connection conn = DatabaseManager.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    employees.add(new EmployeeRecord(
                            rs.getString("emp_name"),
                            rs.getInt("emp_id"),
                            rs.getInt("emp_age"),
                            rs.getString("department_name"),
                            rs.getString("emp_position"),
                            rs.getInt("salary"),
                            LocalDate.parse(rs.getString("hire_date"))
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employees;
        }
        return employees;
    }

    public boolean addEmployee(EmployeeRecord employee) {
        if (employee == null) return false;

        String sql = "INSERT INTO employees (emp_id, emp_name, emp_age, department_name, emp_position, salary, hire_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employee.getEmpId());
            ps.setString(2, employee.getEmpName());
            ps.setInt(3, employee.getEmpAge());
            ps.setString(4, employee.getDepartmentName());
            ps.setString(5, employee.getEmpPosition());
            ps.setInt(6, employee.getSalary());
            ps.setString(7, employee.getHireDate().toString());
            ps.executeUpdate();

            employees.add(employee);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteEmployee(EmployeeRecord employee) {
        if (employee == null) return false;

        String sql = "DELETE FROM employees WHERE emp_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employee.getEmpId());
            ps.executeUpdate();
            employees.remove(employee);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(EmployeeRecord employee, String empName, int empId, int empAge,
                                  String departmentName, String empPosition, int salary, LocalDate hireDate) {
        if (employee == null) return false;

        int oldEmpId = employee.getEmpId();
        String sql = "UPDATE employees SET emp_id=?, emp_name=?, emp_age=?, department_name=?, emp_position=?, salary=?, hire_date=? " +
                     "WHERE emp_id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ps.setString(2, empName);
            ps.setInt(3, empAge);
            ps.setString(4, departmentName);
            ps.setString(5, empPosition);
            ps.setInt(6, salary);
            ps.setString(7, hireDate.toString());
            ps.setInt(8, oldEmpId);
            ps.executeUpdate();

            employee.setEmpName(empName);
            employee.setEmpId(empId);
            employee.setEmpAge(empAge);
            employee.setDepartmentName(departmentName);
            employee.setEmpPosition(empPosition);
            employee.setSalary(salary);
            employee.setHireDate(hireDate);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
