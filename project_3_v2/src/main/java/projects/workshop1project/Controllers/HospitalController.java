package projects.workshop1project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projects.workshop1project.PatientRecord;
import projects.workshop1project.PatientStore;

import java.time.LocalDate;

public class HospitalController {

    @FXML private TextField         nameFld;
    @FXML private DatePicker        dobPck;
    @FXML private RadioButton       maleRB;
    @FXML private RadioButton       femaleRB;
    private       ToggleGroup       genderGroup;
    @FXML private ChoiceBox<String> triageChoice;
    @FXML private TextField         phoneFld;
    @FXML private TextField         idFld;
    @FXML private TextField         doctorFld;
    @FXML private TextField         symptomsFld;
    @FXML private Text              msgTxt;

    @FXML private TableView<PatientRecord>           table;
    @FXML private TableColumn<PatientRecord, String> colName;
    @FXML private TableColumn<PatientRecord, String> colDob;
    @FXML private TableColumn<PatientRecord, String> colGender;
    @FXML private TableColumn<PatientRecord, String> colTriage;
    @FXML private TableColumn<PatientRecord, String> colPhone;
    @FXML private TableColumn<PatientRecord, String> colId;
    @FXML private TableColumn<PatientRecord, String> colDoctor;
    @FXML private TableColumn<PatientRecord, String> colSymptoms;

    @FXML private Button createBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button clearBtn;
    @FXML private Button summaryBtn;

    private final PatientStore store = new PatientStore();
    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    public void initialize() {
        genderGroup = new ToggleGroup();
        maleRB.setToggleGroup(genderGroup);
        femaleRB.setToggleGroup(genderGroup);
        maleRB.setSelected(true);

        triageChoice.getItems().addAll("Low", "Medium", "High", "Critical");
        triageChoice.getSelectionModel().selectFirst();

        phoneFld.textProperty().addListener((obs, oldVal, newVal) -> {
            String digits = newVal.replaceAll("[^\\d]", "");
            if (digits.length() > 8) digits = digits.substring(0, 8);
            if (!newVal.equals(digits)) phoneFld.setText(digits);
        });

        idFld.textProperty().addListener((obs, oldVal, newVal) -> {
            String digits = newVal.replaceAll("[^\\d]", "");
            if (digits.length() > 12) digits = digits.substring(0, 12);
            if (!newVal.equals(digits)) idFld.setText(digits);
        });

        LocalDate minDate = LocalDate.now().minusYears(100);
        LocalDate maxDate = LocalDate.now();
        dobPck.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(minDate) || date.isAfter(maxDate));
            }
        });

        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colTriage.setCellValueFactory(new PropertyValueFactory<>("triage"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colId.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        colSymptoms.setCellValueFactory(new PropertyValueFactory<>("symptoms"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(store.getPatientsList());
        dobPck.setValue(LocalDate.now());

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, selected) -> {
                    if (selected != null) populateForm(selected);
                });

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    boolean has = newVal != null;
                    updateBtn.setDisable(!has);
                    deleteBtn.setDisable(!has);
                });

        for (Button btn : new Button[]{createBtn, updateBtn, deleteBtn, clearBtn, summaryBtn}) {
            btn.setOnMouseReleased(e -> btn.getScene().getRoot().requestFocus());
        }
    }

    @FXML
    private void handleCreate() {
        if (!validateForm()) return;
        store.addPatient(buildRecordFromForm());
        clearForm();
        unfocusButtons();
        msgSuccess("Patient record created successfully!");
    }

    @FXML
    private void handleUpdate() {
        PatientRecord selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) { msgError("Please select a record to update."); return; }
        if (!validateForm()) return;

        store.updatePatient(selected,
                nameFld.getText(),
                dobPck.getValue().toString(),
                selectedGenderText(),
                triageChoice.getValue(),
                phoneFld.getText(),
                idFld.getText(),
                doctorFld.getText(),
                symptomsFld.getText());

        table.refresh();
        clearForm();
        table.getSelectionModel().clearSelection();
        unfocusButtons();
        msgSuccess("Patient record updated successfully!");
    }

    @FXML
    private void handleDelete() {
        PatientRecord selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) { msgError("Please select a record to delete."); return; }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete record for \"" + selected.getFullName() + "\"?",
                ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText("Confirm Deletion");
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                store.deletePatient(selected);
                clearForm();
                unfocusButtons();
                msgSuccess("Record deleted.");
            }
        });
    }

    @FXML
    private void handleClear() {
        clearForm();
        table.getSelectionModel().clearSelection();
        unfocusButtons();
        msgTxt.setText("");
    }

    @FXML
    private void handleSummary() {
        unfocusButtons();
        long low      = store.getPatientsList().stream().filter(r -> r.getTriage().equals("Low")).count();
        long medium   = store.getPatientsList().stream().filter(r -> r.getTriage().equals("Medium")).count();
        long high     = store.getPatientsList().stream().filter(r -> r.getTriage().equals("High")).count();
        long critical = store.getPatientsList().stream().filter(r -> r.getTriage().equals("Critical")).count();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient Summary");
        alert.setHeaderText("Total records: " + store.getPatientsList().size());
        alert.setContentText(
                "Low: "      + low      + "\n" +
                "Medium: "   + medium   + "\n" +
                "High: "     + high     + "\n" +
                "Critical: " + critical
        );
        alert.showAndWait();
    }

    private boolean validateForm() {
        if (nameFld.getText().isBlank()   ||
                phoneFld.getText().isBlank()  ||
                idFld.getText().isBlank()     ||
                doctorFld.getText().isBlank() ||
                symptomsFld.getText().isBlank()) {
            msgError("All fields are required!");
            return false;
        }
        if (phoneFld.getText().length() < 8) {
            msgError("Phone number must be 8 digits.");
            return false;
        }
        if (idFld.getText().length() < 12) {
            msgError("National ID must be 12 digits.");
            return false;
        }
        return true;
    }

    private PatientRecord buildRecordFromForm() {
        return new PatientRecord(
                nameFld.getText(),
                dobPck.getValue().toString(),
                selectedGenderText(),
                triageChoice.getValue(),
                phoneFld.getText(),
                idFld.getText(),
                doctorFld.getText(),
                symptomsFld.getText()
        );
    }

    private String selectedGenderText() {
        RadioButton sel = (RadioButton) genderGroup.getSelectedToggle();
        return sel != null ? sel.getText() : "Male";
    }

    private void populateForm(PatientRecord r) {
        nameFld.setText(r.getFullName());
        dobPck.setValue(LocalDate.parse(r.getDob()));
        if ("Female".equals(r.getGender())) femaleRB.setSelected(true);
        else                                maleRB.setSelected(true);
        triageChoice.setValue(r.getTriage());
        phoneFld.setText(r.getPhone());
        idFld.setText(r.getNationalId());
        doctorFld.setText(r.getDoctor());
        symptomsFld.setText(r.getSymptoms());
        msgTxt.setText("");
    }

    private void clearForm() {
        nameFld.clear();
        phoneFld.clear();
        idFld.clear();
        doctorFld.clear();
        symptomsFld.clear();
        dobPck.setValue(LocalDate.now());
        triageChoice.getSelectionModel().selectFirst();
        maleRB.setSelected(true);
    }

    private void unfocusButtons() {
        if (createBtn.getScene() != null)
            createBtn.getScene().getRoot().requestFocus();
    }

    private void msgSuccess(String text) {
        msgTxt.setFill(Color.web("#1a7a1a"));
        msgTxt.setText(text);
    }

    private void msgError(String text) {
        msgTxt.setFill(Color.RED);
        msgTxt.setText(text);
    }
}
