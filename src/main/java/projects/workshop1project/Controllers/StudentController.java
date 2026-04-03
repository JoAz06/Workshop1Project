package projects.workshop1project.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML private TableColumn<StudentRecord, String> genderCol;
    @FXML private Slider gpa;
    @FXML private TableColumn<StudentRecord, Integer> gpaCol;
    @FXML private Label gpalabel;
    @FXML private TableView<StudentRecord> studentTable;


    public void initialize(){
        //Gpa slider listener to update the label when the value is changed
        gpa.valueProperty().addListener((observable, oldValue, newValue) -> {
            gpalabel.setText("Gpa : "+String.valueOf(newValue.intValue()));
        });
        degree.getItems().addAll("Computer Science", "Mecanical Engineering", "Phylosophie");
        degree.getSelectionModel().selectFirst();
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("degree"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        ObservableList<StudentRecord> students = StudentStore.getStudentList();
        studentTable.setItems(students);
//        personsTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
//            Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();
//            if(selectedPerson != null) {
//                nameFld.setText(selectedPerson.getName());
//                ageFld.setText(Integer.toString(selectedPerson.getAge()));
//                addressFld.setText(selectedPerson.getAddress());
//            }
//        });
    }

    @FXML
    void handleCreate(ActionEvent event) {

    }

    @FXML
    void handleDelete(ActionEvent event) {

    }

    @FXML
    void handleUpdate(ActionEvent event) {

    }

}



