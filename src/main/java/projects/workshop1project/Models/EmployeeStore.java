package projects.workshop1project.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class EmployeeStore {
    private static final ObservableList<EmployeeRecord> employees = FXCollections.observableArrayList();

    public ObservableList<EmployeeRecord> getEmployeesList(){
        return employees;
    }
   public void addEmployees(EmployeeRecord employee) {
       if (employee != null) {
           employees.add(employee);
       }
   }
   public void removeEmployees(EmployeeRecord employee) {
       if (employee != null) {
           employees.remove(employee);
       }
   }
   public void updateEmployees(EmployeeRecord employee ,String empName , int empId , int empAge , String departmentName , String empPosition , int salary , LocalDate hireDate ){
      if (employee!= null) {
          employee.setEmpName(empName);
          employee.setEmpId(empId);
          employee.setEmpAge(empAge);
          employee.setDepartmentName(departmentName);
          employee.setEmpPosition(empPosition);
          employee.setSalary(salary);
          employee.setHireDate(hireDate);
      }
   }
   }

