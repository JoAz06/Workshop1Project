package projects.workshop1project.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class EmployeeRecord {
    private SimpleStringProperty empName;
    private SimpleIntegerProperty empId;
    private SimpleIntegerProperty empAge;
    private SimpleStringProperty departmentName;
    private SimpleStringProperty empPosition;
    private SimpleIntegerProperty salary;
    private SimpleObjectProperty<LocalDate> hireDate;

    public EmployeeRecord(String empName , int empId , int empAge , String departmentName , String empPosition , int salary , LocalDate hireDate){
        this.empName= new SimpleStringProperty(empName);
        this.empId = new SimpleIntegerProperty(empId);
        this.empAge = new SimpleIntegerProperty(empAge);
        this.departmentName = new SimpleStringProperty(departmentName);
        this.empPosition = new SimpleStringProperty(empPosition);
        this.salary = new SimpleIntegerProperty(salary);
        this.hireDate = new SimpleObjectProperty<LocalDate>(hireDate);

    }
    public String getEmpName(){ return empName.get();}
    public void setEmpName(String n){ this.empName.set(n);}
    public StringProperty getEmpNameProperty(){ return empName;}

    public int getEmpId(){ return empId.get();}
    public void setEmpId(int n){this.empId.set(n);}
    public IntegerProperty getEmpIdProperty(){return empId;}

    public int getEmpAge(){return empAge.get();}
    public void setEmpAge(int n){this.empAge.set(n);}
    public IntegerProperty getEmpAgeProperty(){return empAge;}

    public String getDepartmentName(){return departmentName.get();}
    public void setDepartmentName(String n){this.departmentName.set(n);}
    public StringProperty getDepartmentNameProperty(){return departmentName;}

    public String getEmpPosition(){return empPosition.get();}
    public void setEmpPosition(String n){this.empPosition.set(n);}
    public StringProperty getEmpPositionProperty(){return empPosition;}

    public int getSalary(){return salary.get();}
    public void setSalary(int n){this.salary.set(n);}
    public IntegerProperty getSalaryProperty(){return salary;}

    public LocalDate getHireDate(){ return hireDate.get();}
    public void setHireDate(LocalDate hb){this.hireDate.set(hb);}
    public ObjectProperty<LocalDate> getHireDateProperty(){return hireDate;}

}
