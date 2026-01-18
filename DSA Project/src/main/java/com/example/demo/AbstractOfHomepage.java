package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AbstractOfHomepage {

    abstract protected void switchToAbout(ActionEvent event) throws IOException;
    abstract protected void switchToStatus(ActionEvent event) throws IOException;
    abstract protected void switchToChat(ActionEvent event) throws IOException;
    abstract protected void switchToChatBot(ActionEvent event) throws IOException;
    abstract protected void signOutMethod(ActionEvent event) throws IOException;

    @FXML
    protected void switchToHomePage(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        Parent root2 = loader.load();

        homepagecontroller home = loader.getController();
        home.requestSection();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        stage.setScene(scene2);
        stage.show();

    }
}
