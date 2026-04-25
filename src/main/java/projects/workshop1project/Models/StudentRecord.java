package projects.workshop1project.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class StudentRecord {
    private final SimpleIntegerProperty studentId;
    private final SimpleStringProperty fname;
    private final SimpleObjectProperty<LocalDate> dob;
    private final SimpleStringProperty address;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty degree;
    private final SimpleIntegerProperty gpa;

    public StudentRecord(String fname, LocalDate dob, String address, String gender, String degree, int gpa) {
        this(0, fname, dob, address, gender, degree, gpa);
    }

    public StudentRecord(int studentId, String fname, LocalDate dob, String address, String gender, String degree, int gpa) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.fname = new SimpleStringProperty(fname);
        this.dob = new SimpleObjectProperty<>(dob);
        this.address = new SimpleStringProperty(address);
        this.gender = new SimpleStringProperty(gender);
        this.degree = new SimpleStringProperty(degree);
        this.gpa = new SimpleIntegerProperty(gpa);
    }

    public int getStudentId() { return studentId.get(); }
    public void setStudentId(int studentId) { this.studentId.set(studentId); }
    public IntegerProperty studentIdProperty() { return studentId; }

    public String getFname() { return fname.get(); }
    public void setFname(String fname) { this.fname.set(fname); }
    public StringProperty fnameProperty() { return fname; }

    public LocalDate getDob() { return dob.get(); }
    public void setDob(LocalDate dob) { this.dob.set(dob); }
    public ObjectProperty<LocalDate> dobProperty() { return dob; }

    public String getAddress() { return address.get(); }
    public void setAddress(String address) { this.address.set(address); }
    public StringProperty addressProperty() { return address; }

    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
    public StringProperty genderProperty() { return gender; }

    public String getDegree() { return degree.get(); }
    public void setDegree(String degree) { this.degree.set(degree); }
    public StringProperty degreeProperty() { return degree; }

    public int getGpa() { return gpa.get(); }
    public void setGpa(int gpa) { this.gpa.set(gpa); }
    public IntegerProperty gpaProperty() { return gpa; }
}
