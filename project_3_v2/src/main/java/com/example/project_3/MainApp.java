package com.example.project_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("login.fxml"));
        Scene scene = new Scene(loader.load(), 320, 260);

        LoginController loginCtrl = loader.getController();
        loginCtrl.setPrimaryStage(stage);

        stage.setTitle("Sign In");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
