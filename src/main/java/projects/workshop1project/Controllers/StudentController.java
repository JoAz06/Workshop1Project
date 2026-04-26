package projects.workshop1project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projects.workshop1project.Models.StudentRecord;
import projects.workshop1project.Models.StudentStore;
import projects.workshop1project.NavigationUtil;

import java.io.IOException;
import java.time.LocalDate;

public class StudentController {
    @FXML private TextField address;
    @FXML private TableColumn<StudentRecord, String> addressCol;
    @FXML private Button create;
    @FXML private Button backBtn;
    @FXML private ChoiceBox<String> degree;
    @FXML private TableColumn<StudentRecord, String> degreeCol;
    @FXML private DatePicker dob;
    @FXML private TableColumn<StudentRecord, LocalDate> dobCol;
    @FXML private TextField fname;
    @FXML private TableColumn<StudentRecord, String> fnameCol;
    @FXML private TableColumn<StudentRecord, Integer> studentIdCol;
    @FXML private ToggleGroup gender;
    @FXML private RadioButton maleRB;
    @FXML private RadioButton femaleRB;
    @FXML private TableColumn<StudentRecord, String> genderCol;
    @FXML private Slider gpa;
    @FXML private TableColumn<StudentRecord, Integer> gpaCol;
    @FXML private Label gpalabel;
    @FXML private TableView<StudentRecord> studentTable;
    @FXML private Text error;

    private final StudentStore studentStore = new StudentStore();

    public void initialize() {
        gpa.valueProperty().addListener((observable, oldValue, newValue) ->
                gpalabel.setText("Gpa : " + newValue.intValue()));

        degree.getItems().addAll("Computer Science", "Mechanical Engineering", "Philosophy");
        degree.getSelectionModel().selectFirst();
        dob.setValue(LocalDate.now());
        maleRB.setUserData("Male");
        femaleRB.setUserData("Female");

        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("degree"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        studentTable.setItems(studentStore.getStudentList());

        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selectedPerson) -> {
            if (selectedPerson != null) {
                fname.setText(selectedPerson.getFname());
                dob.setValue(selectedPerson.getDob());
                if ("Male".equals(selectedPerson.getGender())) {
                    maleRB.setSelected(true);
                } else {
                    femaleRB.setSelected(true);
                }
                degree.setValue(selectedPerson.getDegree());
                address.setText(selectedPerson.getAddress());
                gpa.setValue(selectedPerson.getGpa());
                error.setText("");
            }
        });
    }

    @FXML
    void handleCreate(ActionEvent event) {
        if (validate()) {
            StudentRecord student = new StudentRecord(
                    fname.getText(),
                    dob.getValue(),
                    address.getText(),
                    gender.getSelectedToggle().getUserData().toString(),
                    degree.getValue(),
                    (int) gpa.getValue()
            );

            if (studentStore.addStudent(student)) {
                clearFields();
                error.setText("");
            } else {
                error.setText("Could not save student to database.");
            }
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        StudentRecord selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            error.setText("Select a student first");
            return;
        }

        if (studentStore.deleteStudent(selected)) {
            clearFields();
            studentTable.getSelectionModel().clearSelection();
            error.setText("");
        } else {
            error.setText("Could not delete student from database.");
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        StudentRecord selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            error.setText("Select a student first");
            return;
        }

        if (validate()) {
            boolean updated = studentStore.updateStudent(
                    selected,
                    fname.getText(),
                    dob.getValue(),
                    address.getText(),
                    gender.getSelectedToggle().getUserData().toString(),
                    degree.getValue(),
                    (int) gpa.getValue()
            );

            if (updated) {
                studentTable.refresh();
                clearFields();
                studentTable.getSelectionModel().clearSelection();
                error.setText("");
            } else {
                error.setText("Could not update student in database.");
            }
        }
    }

    @FXML
    private void handleBack() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        NavigationUtil.goToHub(stage);
    }

    private boolean validate() {
        String message = "";
        if (fname.getText().isEmpty()) {
            message = "Don't leave name blank";
        } else if (dob.getValue() == null) {
            message = "Don't leave date of birth blank";
        } else if (address.getText().isEmpty()) {
            message = "Don't leave address blank";
        } else if (gender.getSelectedToggle() == null) {
            message = "Select gender";
        } else if (degree.getValue() == null) {
            message = "Select degree";
        }

        error.setText(message);
        return message.isEmpty();
    }

    private void clearFields() {
        fname.clear();
        dob.setValue(LocalDate.now());
        maleRB.setSelected(true);
        degree.getSelectionModel().selectFirst();
        address.clear();
        gpa.setValue(0);
    }
}
