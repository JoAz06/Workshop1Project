package com.example.project_3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientRecord {

    private final StringProperty fullName   = new SimpleStringProperty();
    private final StringProperty dob        = new SimpleStringProperty();
    private final StringProperty gender     = new SimpleStringProperty();
    private final StringProperty triage     = new SimpleStringProperty();
    private final StringProperty phone      = new SimpleStringProperty();
    private final StringProperty nationalId = new SimpleStringProperty();
    private final StringProperty doctor     = new SimpleStringProperty();
    private final StringProperty symptoms   = new SimpleStringProperty();

    public PatientRecord(String fullName, String dob, String gender,
                         String triage, String phone, String nationalId,
                         String doctor, String symptoms) {
        this.fullName.set(fullName);
        this.dob.set(dob);
        this.gender.set(gender);
        this.triage.set(triage);
        this.phone.set(phone);
        this.nationalId.set(nationalId);
        this.doctor.set(doctor);
        this.symptoms.set(symptoms);
    }

    public String getFullName()                { return fullName.get(); }
    public void   setFullName(String v)        { fullName.set(v); }
    public StringProperty fullNameProperty()   { return fullName; }

    public String getDob()                     { return dob.get(); }
    public void   setDob(String v)             { dob.set(v); }
    public StringProperty dobProperty()        { return dob; }

    public String getGender()                  { return gender.get(); }
    public void   setGender(String v)          { gender.set(v); }
    public StringProperty genderProperty()     { return gender; }

    public String getTriage()                  { return triage.get(); }
    public void   setTriage(String v)          { triage.set(v); }
    public StringProperty triageProperty()     { return triage; }

    public String getPhone()                   { return phone.get(); }
    public void   setPhone(String v)           { phone.set(v); }
    public StringProperty phoneProperty()      { return phone; }

    public String getNationalId()              { return nationalId.get(); }
    public void   setNationalId(String v)      { nationalId.set(v); }
    public StringProperty nationalIdProperty() { return nationalId; }

    public String getDoctor()                  { return doctor.get(); }
    public void   setDoctor(String v)          { doctor.set(v); }
    public StringProperty doctorProperty()     { return doctor; }

    public String getSymptoms()                { return symptoms.get(); }
    public void   setSymptoms(String v)        { symptoms.set(v); }
    public StringProperty symptomsProperty()   { return symptoms; }
}
