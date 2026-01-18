package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root1 =FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene s1 = new Scene(root1);
        stage.setTitle("Connectify");
        stage.setScene(s1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}