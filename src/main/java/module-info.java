module projects.workshop1project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens projects.workshop1project.Controllers to javafx.fxml;
    opens projects.workshop1project.Models to javafx.base;
    exports projects.workshop1project;
}
