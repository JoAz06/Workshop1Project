package projects.workshop1project.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import projects.workshop1project.Models.EmployeeRecord;
import projects.workshop1project.Models.EmployeeStore;

import java.time.LocalDate;

public class EmployeeController {
   @FXML
   private Text error;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField ageField;
    @FXML
    private ChoiceBox<String> departmentBox;
    @FXML
    private ChoiceBox<String> positionBox;
    @FXML
    private Slider salarySlider;
    @FXML
    private Label salaryLabel;
    @FXML
    private DatePicker dateChooser;
    @FXML
    private Button update , delete , create;
    @FXML private TableView<EmployeeRecord> employeeTable;
    @FXML private TableColumn<EmployeeRecord, String> nameCol;
    @FXML private TableColumn<EmployeeRecord , Integer> ageCol;
    @FXML private TableColumn<EmployeeRecord, Integer> idCol;
    @FXML private TableColumn<EmployeeRecord , Integer> salaryCol;
    @FXML private TableColumn<EmployeeRecord, String> departmentCol;
    @FXML private TableColumn<EmployeeRecord, String> positionCol;
    @FXML private TableColumn<EmployeeRecord, LocalDate> hireDateCol;

    public void initialize() {
      salarySlider.setValue(800);
      salaryLabel.setText("Salary: 800$");
        salarySlider.valueProperty().addListener((observable, oldVal, newVal) -> {
            salaryLabel.setText("Salary: " + String.valueOf(newVal.intValue())+ "$");
        });
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

        nameCol.setCellValueFactory(new PropertyValueFactory<>("EmpName"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("EmpAge"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("EmpPosition"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("HireDate"));
        ObservableList<EmployeeRecord> employees = EmployeeStore.getEmployeesList();
        employeeTable.setItems(employees);

        employeeTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            EmployeeRecord selectedPerson = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedPerson != null){
                nameField.setText(selectedPerson.getEmpName());
                idField.setText(String.valueOf(selectedPerson.getEmpId()));
                ageField.setText(String.valueOf(selectedPerson.getEmpAge()));
                salarySlider.setValue(selectedPerson.getSalary());
                departmentBox.setValue(selectedPerson.getDepartmentName());
                positionBox.setValue(selectedPerson.getEmpPosition());
                dateChooser.setValue(selectedPerson.getHireDate());
            }
        });
    }
    @FXML
    void handleCreate(ActionEvent event){
        if (validate()){
           EmployeeRecord employee = new EmployeeRecord(nameField.getText() , Integer.parseInt(idField.getText().trim()), Integer.parseInt(ageField.getText().trim()), departmentBox.getValue() , positionBox.getValue() , (int)salarySlider.getValue() , dateChooser.getValue());
           employeeTable.getItems().add(employee);
           clearFields();
        }

    }
    @FXML
    void handleDelete(ActionEvent event){
     EmployeeRecord selected = employeeTable.getSelectionModel().getSelectedItem();

         employeeTable.getItems().remove(selected);
         clearFields();


    }
    @FXML
    void handleUpdate(ActionEvent event){
        EmployeeRecord selected = employeeTable.getSelectionModel().getSelectedItem();
        if ( validate()){
            selected.setEmpName(nameField.getText());
            selected.setEmpId(Integer.parseInt(idField.getText().trim()));
            selected.setEmpAge(Integer.parseInt(ageField.getText().trim()));
            selected.setDepartmentName(departmentBox.getValue());
            selected.setEmpPosition(positionBox.getValue());
            selected.setSalary((int)salarySlider.getValue());
            selected.setHireDate(dateChooser.getValue());
            clearFields();
        }
    }
    private void clearFields(){
        nameField.clear();
        ageField.clear();
        idField.clear();
        salarySlider.setValue(0);
        departmentBox.setValue("Software Engineering");
        positionBox.setValue("Recruit");
        dateChooser.setValue(LocalDate.now());
    }
   private boolean validate(){
        String message = "";

        if(nameField.getText().isEmpty()){
            message += "Name field is empty\n";
        }
        if(idField.getText().trim().isEmpty()){
            message += "ID field is empty\n";
        }
        else {
            int id = Integer.parseInt(idField.getText().trim());
            if (id <= 0){
                message += "ID must be greater than 0\n";
            }
        }
        if (ageField.getText().trim().isEmpty()){
            message+= "Age field is empty\n";
        }
        else{
            int age = Integer.parseInt(ageField.getText().trim());
            if (age <= 18 || age > 64){
                message += "Age must be between 18 and 64\n";
            }
        }
     if (dateChooser.getValue() == null){
         message += "Hire date is empty\n";
     }
     else if (dateChooser.getValue().isAfter(LocalDate.now())){
         message += "Hire date cannot be in the future\n";
     }
     if (!message.isEmpty()){
         error.setText(message);
         return false;

     } else{
         error.setText("");
         return true;
     }



   }
}