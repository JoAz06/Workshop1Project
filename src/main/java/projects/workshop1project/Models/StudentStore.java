package projects.workshop1project.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class StudentStore {
    private static final ObservableList<StudentRecord> students = FXCollections.observableArrayList();
    static{
        students.addAll(
                new StudentRecord("John Cena", LocalDate.parse("2000-01-23"), "NY", "Male","Computer Science",90),
                new StudentRecord("Joe Biden", LocalDate.parse("1998-11-05"), "Boston", "Male","Computer Science",23),
                new StudentRecord("Sarah Connor", LocalDate.parse("2000-01-23"), "NY", "Female","Computer Science",90),
                new StudentRecord("Jawad Haidar", LocalDate.parse("2006-06-19"), "Lebanon", "Male","Computer Science",88)
        );
    }
    public StudentStore() {
    }

    public ObservableList<StudentRecord> getStudentList() {
        return students;
    }

    public void addStudent(StudentRecord student){
        if(student != null)
            students.add(student);
    }

    public void deleteStudent(StudentRecord student){
        if(student != null)
            students.remove(student);
    }

    public void updateStudent(StudentRecord student, String fname, LocalDate dob, String address, String gender, String degree, int gpa) {
        if(student != null){
            student.setFname(fname);
            student.setDob(dob);
            student.setAddress(address);
            student.setGender(gender);
            student.setDegree(degree);
            student.setGpa(gpa);
        }
    }
}
