module projects.workshop1project {
    requires javafx.controls;
    requires javafx.fxml;

    opens projects.workshop1project.Controllers to javafx.fxml;
    exports projects.workshop1project;
}
