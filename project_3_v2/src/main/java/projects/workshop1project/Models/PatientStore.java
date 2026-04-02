package projects.workshop1project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientStore {

    private final ObservableList<PatientRecord> patients =
            FXCollections.observableArrayList();

    public ObservableList<PatientRecord> getPatientsList() {
        return patients;
    }

    public void addPatient(PatientRecord p) {
        if (p != null) patients.add(p);
    }

    public void deletePatient(PatientRecord p) {
        if (p != null) patients.remove(p);
    }

    public void updatePatient(PatientRecord p, String fullName, String dob,
                              String gender, String triage, String phone,
                              String nationalId, String doctor, String symptoms) {
        if (p != null) {
            p.setFullName(fullName);
            p.setDob(dob);
            p.setGender(gender);
            p.setTriage(triage);
            p.setPhone(phone);
            p.setNationalId(nationalId);
            p.setDoctor(doctor);
            p.setSymptoms(symptoms);
        }
    }
}
