package projects.workshop1project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projects.workshop1project.Controllers.HubController;

import java.io.IOException;

public class NavigationUtil {

    public static void goToHub(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                NavigationUtil.class.getResource("/projects/workshop1project/Views/hub.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);

        HubController hubCtrl = loader.getController();
        hubCtrl.setPrimaryStage(stage);

        stage.setTitle("Project Hub");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
