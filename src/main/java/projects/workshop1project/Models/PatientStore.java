package projects.workshop1project.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projects.workshop1project.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientStore {

    private final ObservableList<PatientRecord> patients = FXCollections.observableArrayList();

    public ObservableList<PatientRecord> getPatientsList() {
        if(patients.isEmpty()) {
            String sql = "SELECT * FROM patients";

            try (Connection conn = DatabaseManager.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    patients.add(new PatientRecord(
                            rs.getString("full_name"),
                            rs.getString("dob"),
                            rs.getString("gender"),
                            rs.getString("triage"),
                            rs.getString("phone"),
                            rs.getString("national_id"),
                            rs.getString("doctor"),
                            rs.getString("symptoms")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return patients;
        }
        return patients;
    }

    public boolean addPatient(PatientRecord p) {
        if (p == null) return false;

        String sql = "INSERT INTO patients (national_id, full_name, dob, gender, triage, phone, doctor, symptoms) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNationalId());
            ps.setString(2, p.getFullName());
            ps.setString(3, p.getDob());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getTriage());
            ps.setString(6, p.getPhone());
            ps.setString(7, p.getDoctor());
            ps.setString(8, p.getSymptoms());
            ps.executeUpdate();

            patients.add(p);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deletePatient(PatientRecord p) {
        if (p == null) return false;

        String sql = "DELETE FROM patients WHERE national_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNationalId());
            ps.executeUpdate();
            patients.remove(p);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePatient(PatientRecord p, String fullName, String dob,
                                 String gender, String triage, String phone,
                                 String nationalId, String doctor, String symptoms) {
        if (p == null) return false;

        String oldNationalId = p.getNationalId();
        String sql = "UPDATE patients SET national_id=?, full_name=?, dob=?, gender=?, triage=?, phone=?, doctor=?, symptoms=? " +
                     "WHERE national_id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nationalId);
            ps.setString(2, fullName);
            ps.setString(3, dob);
            ps.setString(4, gender);
            ps.setString(5, triage);
            ps.setString(6, phone);
            ps.setString(7, doctor);
            ps.setString(8, symptoms);
            ps.setString(9, oldNationalId);
            ps.executeUpdate();

            p.setFullName(fullName);
            p.setDob(dob);
            p.setGender(gender);
            p.setTriage(triage);
            p.setPhone(phone);
            p.setNationalId(nationalId);
            p.setDoctor(doctor);
            p.setSymptoms(symptoms);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
