package projects.workshop1project.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class EmployeeRecord {
    private final SimpleStringProperty empName;
    private final SimpleIntegerProperty empId;
    private final SimpleIntegerProperty empAge;
    private final SimpleStringProperty departmentName;
    private final SimpleStringProperty empPosition;
    private final SimpleIntegerProperty salary;
    private final SimpleObjectProperty<LocalDate> hireDate;

    public EmployeeRecord(String empName, int empId, int empAge, String departmentName,
                          String empPosition, int salary, LocalDate hireDate) {
        this.empName = new SimpleStringProperty(empName);
        this.empId = new SimpleIntegerProperty(empId);
        this.empAge = new SimpleIntegerProperty(empAge);
        this.departmentName = new SimpleStringProperty(departmentName);
        this.empPosition = new SimpleStringProperty(empPosition);
        this.salary = new SimpleIntegerProperty(salary);
        this.hireDate = new SimpleObjectProperty<>(hireDate);
    }

    public String getEmpName() { return empName.get(); }
    public void setEmpName(String empName) { this.empName.set(empName); }
    public StringProperty empNameProperty() { return empName; }
    public StringProperty getEmpNameProperty() { return empName; }

    public int getEmpId() { return empId.get(); }
    public void setEmpId(int empId) { this.empId.set(empId); }
    public IntegerProperty empIdProperty() { return empId; }
    public IntegerProperty getEmpIdProperty() { return empId; }

    public int getEmpAge() { return empAge.get(); }
    public void setEmpAge(int empAge) { this.empAge.set(empAge); }
    public IntegerProperty empAgeProperty() { return empAge; }
    public IntegerProperty getEmpAgeProperty() { return empAge; }

    public String getDepartmentName() { return departmentName.get(); }
    public void setDepartmentName(String departmentName) { this.departmentName.set(departmentName); }
    public StringProperty departmentNameProperty() { return departmentName; }
    public StringProperty getDepartmentNameProperty() { return departmentName; }

    public String getEmpPosition() { return empPosition.get(); }
    public void setEmpPosition(String empPosition) { this.empPosition.set(empPosition); }
    public StringProperty empPositionProperty() { return empPosition; }
    public StringProperty getEmpPositionProperty() { return empPosition; }

    public int getSalary() { return salary.get(); }
    public void setSalary(int salary) { this.salary.set(salary); }
    public IntegerProperty salaryProperty() { return salary; }
    public IntegerProperty getSalaryProperty() { return salary; }

    public LocalDate getHireDate() { return hireDate.get(); }
    public void setHireDate(LocalDate hireDate) { this.hireDate.set(hireDate); }
    public ObjectProperty<LocalDate> hireDateProperty() { return hireDate; }
    public ObjectProperty<LocalDate> getHireDateProperty() { return hireDate; }
}
