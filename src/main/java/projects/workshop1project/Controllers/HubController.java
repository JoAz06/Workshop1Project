package projects.workshop1project.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import projects.workshop1project.Controllers.HospitalController;

import java.io.IOException;
import java.io.InputStream;

public class HubController {

    @FXML private ImageView hubImageView;

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void initialize() {
        InputStream imgStream = getClass().getResourceAsStream("/projects/workshop1project/Images/hub_bg.jpg");
        if (imgStream != null) {
            hubImageView.setImage(new Image(imgStream));
        }
    }

    @FXML
    private void handleHospital() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/projects/workshop1project/Views/hospital.fxml"));
        Scene hospitalScene = new Scene(loader.load(), 1000, 700);

        HospitalController hospitalCtrl = loader.getController();
        hospitalCtrl.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Hospital System – Check-in");
        primaryStage.setResizable(false);
        primaryStage.setScene(hospitalScene);
    }

    @FXML
    private void handleSchool() {
        System.out.println("School – coming soon");
    }

    @FXML
    private void handleOffice() {
        System.out.println("Office – coming soon");
    }
}
