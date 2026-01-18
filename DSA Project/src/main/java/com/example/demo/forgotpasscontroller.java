package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java .io.File;
import java .io.FileWriter;
import java .util.*;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java .io.*;

public class forgotpasscontroller {
    @FXML
    private Button sbmt1;
    @FXML
    private TextField hintfield;
    private String hint;
    @FXML
    void setHint(){
        hint=hintfield.getText();
    }

    public boolean getHintFromFile() {
        setHint(); // Set the hint

        boolean isHintFound = false;
        File info = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");

        try (FileReader fileReader = new FileReader(info);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line by colon
                String[] parts = line.split(":");
                // Get the last part
                String thirdPart = parts[parts.length - 3];
                String secondPart = parts[parts.length - 4];

                if (thirdPart.equals(hint)) {
                    isHintFound = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert!");
                    alert.setContentText("Your password is "+secondPart);
                    alert.showAndWait();
                    break; // Exit the loop as soon as the hint is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isHintFound) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Wrong Username");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void checkChange(ActionEvent event) throws IOException {
        if(getHintFromFile()) {
            Parent root4 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene4 = new Scene(root4);
            stage.setScene(scene4);
            stage.show();
        }
        else{
            System.out.println("No hint is being matched");
        }
    }

    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
        Parent root4 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene4 = new Scene(root4);
        stage.setScene(scene4);
        stage.show();
    }


}
