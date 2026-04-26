package projects.workshop1project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projects.workshop1project.Models.EmployeeRecord;
import projects.workshop1project.Models.EmployeeStore;
import projects.workshop1project.NavigationUtil;

import java.io.IOException;
import java.time.LocalDate;

public class EmployeeController {
    @FXML private Text error;
    @FXML private TextField nameField;
    @FXML private TextField idField;
    @FXML private TextField ageField;
    @FXML private ChoiceBox<String> departmentBox;
    @FXML private ChoiceBox<String> positionBox;
    @FXML private Slider salarySlider;
    @FXML private Label salaryLabel;
    @FXML private DatePicker dateChooser;
    @FXML private Button update;
    @FXML private Button delete;
    @FXML private Button create;
    @FXML private Button backBtn;
    @FXML private TableView<EmployeeRecord> employeeTable;
    @FXML private TableColumn<EmployeeRecord, String> nameCol;
    @FXML private TableColumn<EmployeeRecord, Integer> ageCol;
    @FXML private TableColumn<EmployeeRecord, Integer> idCol;
    @FXML private TableColumn<EmployeeRecord, Integer> salaryCol;
    @FXML private TableColumn<EmployeeRecord, String> departmentCol;
    @FXML private TableColumn<EmployeeRecord, String> positionCol;
    @FXML private TableColumn<EmployeeRecord, LocalDate> hireDateCol;

    private final EmployeeStore employeeStore = new EmployeeStore();

    public void initialize() {
        salarySlider.setValue(800);
        salaryLabel.setText("Salary: 800$");
        dateChooser.setValue(LocalDate.now());

        salarySlider.valueProperty().addListener((observable, oldVal, newVal) ->
                salaryLabel.setText("Salary: " + newVal.intValue() + "$"));

        idField.textProperty().addListener((obs, oldVal, newVal) -> {
            String digits = newVal.replaceAll("[^\\d]", "");
            if (digits.length() > 12) digits = digits.substring(0, 12);
            if (!newVal.equals(digits)) idField.setText(digits);
        });

        ageField.textProperty().addListener((obs, oldVal, newVal) -> {
            String digits = newVal.replaceAll("[^\\d]", "");
            if (digits.length() > 2) digits = digits.substring(0, 2);
            if (!newVal.equals(digits)) ageField.setText(digits);
        });

        departmentBox.getItems().addAll("Software Engineering", "Medicine", "Tutoring");
        departmentBox.setValue("Software Engineering");
        positionBox.getItems().addAll("Manager", "Supervisor", "Recruit", "Intern");
        positionBox.setValue("Recruit");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("empAge"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("empPosition"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        employeeTable.setItems(employeeStore.getEmployeesList());

        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selectedPerson) -> {
            if (selectedPerson != null) {
                nameField.setText(selectedPerson.getEmpName());
                idField.setText(String.valueOf(selectedPerson.getEmpId()));
                ageField.setText(String.valueOf(selectedPerson.getEmpAge()));
                salarySlider.setValue(selectedPerson.getSalary());
                departmentBox.setValue(selectedPerson.getDepartmentName());
                positionBox.setValue(selectedPerson.getEmpPosition());
                dateChooser.setValue(selectedPerson.getHireDate());
                error.setText("");
            }
        });
    }

    @FXML
    void handleCreate(ActionEvent event) {
        if (validate()) {
            EmployeeRecord employee = new EmployeeRecord(
                    nameField.getText(),
                    Integer.parseInt(idField.getText().trim()),
                    Integer.parseInt(ageField.getText().trim()),
                    departmentBox.getValue(),
                    positionBox.getValue(),
                    (int) salarySlider.getValue(),
                    dateChooser.getValue()
            );

            if (employeeStore.addEmployee(employee)) {
                clearFields();
                error.setText("");
            } else {
                error.setText("Could not save employee. ID may already exist.");
            }
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        EmployeeRecord selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            error.setText("Select an employee first");
            return;
        }

        if (employeeStore.deleteEmployee(selected)) {
            clearFields();
            employeeTable.getSelectionModel().clearSelection();
            error.setText("");
        } else {
            error.setText("Could not delete employee from database.");
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        EmployeeRecord selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            error.setText("Select an employee first");
            return;
        }

        if (validate()) {
            boolean updated = employeeStore.updateEmployee(
                    selected,
                    nameField.getText(),
                    Integer.parseInt(idField.getText().trim()),
                    Integer.parseInt(ageField.getText().trim()),
                    departmentBox.getValue(),
                    positionBox.getValue(),
                    (int) salarySlider.getValue(),
                    dateChooser.getValue()
            );

            if (updated) {
                employeeTable.refresh();
                clearFields();
                employeeTable.getSelectionModel().clearSelection();
                error.setText("");
            } else {
                error.setText("Could not update employee. ID may already exist.");
            }
        }
    }

    @FXML
    private void handleBack() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        NavigationUtil.goToHub(stage);
    }

    private void clearFields() {
        nameField.clear();
        ageField.clear();
        idField.clear();
        salarySlider.setValue(800);
        departmentBox.setValue("Software Engineering");
        positionBox.setValue("Recruit");
        dateChooser.setValue(LocalDate.now());
    }

    private boolean validate() {
        String message = "";

        if (nameField.getText().isEmpty()) {
            message += "Name field is empty\n";
        }
        if (idField.getText().trim().isEmpty()) {
            message += "ID field is empty\n";
        } else {
            int id = Integer.parseInt(idField.getText().trim());
            if (id <= 0) {
                message += "ID must be greater than 0\n";
            }
        }
        if (ageField.getText().trim().isEmpty()) {
            message += "Age field is empty\n";
        } else {
            int age = Integer.parseInt(ageField.getText().trim());
            if (age < 18 || age > 64) {
                message += "Age must be between 18 and 64\n";
            }
        }
        if (dateChooser.getValue() == null) {
            message += "Hire date is empty\n";
        } else if (dateChooser.getValue().isAfter(LocalDate.now())) {
            message += "Hire date cannot be in the future\n";
        }

        error.setText(message);
        return message.isEmpty();
    }
}
