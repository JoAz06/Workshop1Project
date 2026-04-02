package projects.workshop1project.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projects.workshop1project.Controllers.HubController;

import java.io.IOException;

public class LoginController {

    private static final String[][] CREDENTIALS = {
        {"Ryan",     "6769"},
        {"Teammate2","pass2"},
        {"Teammate3","pass3"}
    };

    @FXML private TextField     userFld;
    @FXML private PasswordField passFld;
    @FXML private Text          loginError;

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handleSignIn() throws IOException {
        String username = userFld.getText();
        String password = passFld.getText();

        if (username.isBlank() || password.isBlank()) {
            loginError.setText("Username and password are required!");
            return;
        }

        boolean valid = false;
        for (String[] credential : CREDENTIALS) {
            if (credential[0].equals(username) && credential[1].equals(password)) {
                valid = true;
                break;
            }
        }

        if (!valid) {
            loginError.setText("Incorrect username or password.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/projects/workshop1project/Views/hub.fxml"));
        Scene hubScene = new Scene(loader.load(), 900, 600);

        HubController hubCtrl = loader.getController();
        hubCtrl.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Project Hub");
        primaryStage.setResizable(false);
        primaryStage.setScene(hubScene);
    }
}
