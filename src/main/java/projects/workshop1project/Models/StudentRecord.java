package projects.workshop1project.Models;

import javafx.beans.property.*;

import java.time.LocalDate;


public class StudentRecord {
    private SimpleStringProperty fname;
    private SimpleObjectProperty<LocalDate> dob;
    private SimpleStringProperty address;
    private SimpleStringProperty gender;
    private SimpleStringProperty degree;
    private SimpleIntegerProperty gpa;

    public StudentRecord(String fname, LocalDate dob, String address, String gender, String degree, int gpa) {
        this.fname = new SimpleStringProperty(fname);
        this.dob = new SimpleObjectProperty(dob);
        this.address = new SimpleStringProperty(address);
        this.gender = new SimpleStringProperty(gender);
        this.degree = new SimpleStringProperty(degree);
        this.gpa = new SimpleIntegerProperty(gpa);
    }

    public String getFname() {
        return fname.get();
    }
    public void setFname(String fname) {
        this.fname.set(fname);
    }
    public StringProperty fnameProperty() {
        return this.fname;
    }

    public LocalDate getDob() {return dob.get();}
    public void setDob(LocalDate dob) {
        this.dob.set(dob);
    }
    public ObjectProperty dobProperty() {
        return this.dob;
    }

    public String getAddress() {
        return address.get();
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
    public StringProperty addressProperty() {
        return this.address;
    }

    public String getGender() {
        return gender.get();
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }
    public StringProperty genderProperty() {
        return this.gender;
    }

    public String getDegree() {
        return degree.get();
    }
    public void setDegree(String degree) {
        this.degree.set(degree);
    }
    public StringProperty degreeProperty() {
        return this.degree;
    }

    public int getGpa() {
        return gpa.get();
    }
    public void setGpa(int gpa) {
        this.gpa.set(gpa);
    }
    public IntegerProperty gpaProperty() {
        return this.gpa;
    }
}
