package projects.workshop1project.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import projects.workshop1project.Models.StudentRecord;
import projects.workshop1project.Models.StudentStore;

import java.time.LocalDate;


public class StudentController {
    @FXML private TextField address;
    @FXML private TableColumn<StudentRecord, String> addressCol;
    @FXML private Button create;
    @FXML private ChoiceBox<String> degree;
    @FXML private TableColumn<StudentRecord, String> degreeCol;
    @FXML private DatePicker dob;
    @FXML private TableColumn<StudentRecord, LocalDate> dobCol;
    @FXML private TextField fname;
    @FXML private TableColumn<StudentRecord, String> fnameCol;
    @FXML private ToggleGroup gender;
    @FXML private RadioButton maleRB;
    @FXML private RadioButton femaleRB;
    @FXML private TableColumn<StudentRecord, String> genderCol;
    @FXML private Slider gpa;
    @FXML private TableColumn<StudentRecord, Integer> gpaCol;
    @FXML private Label gpalabel;
    @FXML private TableView<StudentRecord> studentTable;
    @FXML private Text error;


    public void initialize(){
        //Gpa slider listener to update the label when the value is changed
        gpa.valueProperty().addListener((observable, oldValue, newValue) -> {
            gpalabel.setText("Gpa : "+String.valueOf(newValue.intValue()));
        });

        //Data initialisation
        degree.getItems().addAll("Computer Science", "Mechanical Engineering", "Philosophy");
        degree.getSelectionModel().selectFirst();
        dob.setValue(LocalDate.now());
        maleRB.setUserData("Male");
        femaleRB.setUserData("Female");

        //Table view configuration
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("degree"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        ObservableList<StudentRecord> students = StudentStore.getStudentList();
        studentTable.setItems(students);

        //Filling values when selecting row in table to update later or delete
        studentTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            StudentRecord selectedPerson = studentTable.getSelectionModel().getSelectedItem();
            if(selectedPerson != null) {
                fname.setText(selectedPerson.getFname());
                dob.setValue(selectedPerson.getDob());
                if(selectedPerson.getGender().equals("Male")) {maleRB.setSelected(true);}
                else{femaleRB.setSelected(true);}
                degree.setValue(selectedPerson.getDegree());
                address.setText(selectedPerson.getAddress());
                gpa.setValue(selectedPerson.getGpa());
            }
        });
    }

    @FXML
    void handleCreate(ActionEvent event) {
        if(validate()){
            studentTable.getItems().add(new StudentRecord(fname.getText(), dob.getValue(), address.getText(), gender.getSelectedToggle().getUserData().toString(),degree.getValue(),(int)gpa.getValue()));
            clearFields();
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        StudentRecord selectedPerson = studentTable.getSelectionModel().getSelectedItem();
        studentTable.getItems().remove(selectedPerson);
        clearFields();
        studentTable.getSelectionModel().clearSelection();
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        if(validate()){
            StudentRecord selectedPerson = studentTable.getSelectionModel().getSelectedItem();
            selectedPerson.setFname(fname.getText());
            selectedPerson.setAddress(address.getText());
            selectedPerson.setGender(gender.getSelectedToggle().getUserData().toString());
            selectedPerson.setDegree(degree.getValue());
            selectedPerson.setAddress(address.getText());
            selectedPerson.setGpa((int)gpa.getValue());
            clearFields();
            studentTable.getSelectionModel().clearSelection();
        }
    }


    private boolean validate(){
        boolean validity = true;
        String message = "";
        if(fname.getText().isEmpty()){
            validity = false;
            message = "Don't leave name blank";
        } else if(address.getText().isEmpty()){
            validity = false;
            message = "Don't leave address blank";
        }

        error.setText(message);
        return validity;
    }

    private void clearFields(){
        fname.clear();
        dob.setValue(LocalDate.now());
        maleRB.setSelected(true);
        degree.getSelectionModel().selectFirst();
        address.clear();
        gpa.setValue(0);
    }
}



