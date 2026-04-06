package projects.workshop1project.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class EmployeeRecord {
    private SimpleStringProperty empName;
    private SimpleStringProperty empId;
    private SimpleStringProperty empAge;
    private SimpleStringProperty departmentName;
    private SimpleStringProperty empPosition;
    private SimpleIntegerProperty salary;
    private SimpleObjectProperty<LocalDate> hireDate;

    public EmployeeRecord(String empName , String empId , String empAge , String departmentName , String empPosition , int salary , LocalDate hireDate){
        this.empName = new SimpleStringProperty(empName);
        this.empId  = new SimpleStringProperty(empId);
        this.empAge = new SimpleStringProperty(empAge);
        this.departmentName = new SimpleStringProperty(departmentName);
        this.empPosition = new SimpleStringProperty(empPosition);
        this.salary = new SimpleIntegerProperty(salary);
        this.hireDate = new SimpleObjectProperty<LocalDate>(hireDate);

    }

}
