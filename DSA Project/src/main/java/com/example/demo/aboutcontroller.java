package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

import java.io.IOException;

import static com.example.demo.HelloController.usernamecopy;

public class aboutcontroller extends homepagecontroller{
@FXML
private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Label l1;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    private String hint;
    @FXML
    public void displayInformation() throws IOException{

        File info = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");

        try (FileReader fileReader = new FileReader(info);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line by colon
                String[] parts = line.split(":");
                if ( parts[0].equals(usernamecopy)) {
                    hint=parts[2];
                    l3.setText(parts[3]);
                    l4.setText(parts[0]);
                    l5.setText(parts[4]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showHint(ActionEvent event) throws IOException{
        l1.setText(hint);
    }


    @FXML
    private void gotoStatus(ActionEvent event) throws IOException {
        super.switchToStatus(event);
    }
    @FXML
    private void gotoChat(ActionEvent event) throws IOException {
        super.switchToChat(event);
    }
    @FXML
    private void gotoChatBot(ActionEvent event) throws IOException {
        super.switchToChatBot(event);
    }

}