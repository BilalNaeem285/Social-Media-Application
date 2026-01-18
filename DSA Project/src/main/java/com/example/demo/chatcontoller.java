package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class chatcontoller extends homepagecontroller{
    @FXML
    private TextFlow chatMaterial;
    @FXML
    private Label chattingPerson;
    @FXML
    private TextField enterChat;
    @FXML
    private TextField searchChat;
    @FXML
    private Button sendChat;
    @FXML
    private Button startChat;
    protected MyArrayList<String> friendLists = new MyArrayList<>();
    String selectedChat;
    @FXML
    private ListView<String> listview2;

    private void getListOfFriends(){
        try {
            File file = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\"+HelloController.usernamecopy+"friendlist.txt");
            FileReader b = new FileReader(file);
            BufferedReader reading = new BufferedReader(b);

            // Clear the userlists to avoid duplication
            friendLists.clear();

            // Iterate over the file list and write each file path to the file
            String line;
            while ((line = reading.readLine()) != null) {
                if(line.equals("--Friend Request's--")){
                    break;
                }
                friendLists.add(line);
            }
            // Close the writer to flush and release resources
            reading.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchFriend(ActionEvent event) throws IOException{
        getListOfFriends();
        listview2.getItems().clear();
        listview2.getItems().addAll(super.searchList(searchChat.getText(), friendLists));

        // Add a listener to capture the selected item
        listview2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedChat = newValue;
            chattingPerson.setText(selectedChat);
            loadChatHistory();
        });
    }

    @FXML
    private void newChat(ActionEvent event) throws IOException{
        if (selectedChat == null || selectedChat.isEmpty()) {
            return;
        }

        String filePath1 = "E:\\oop project\\src\\main\\java\\com\\example\\demo\\"+selectedChat + "friend" + HelloController.usernamecopy+".txt";
        String filePath2 = "E:\\oop project\\src\\main\\java\\com\\example\\demo\\"+HelloController.usernamecopy + "friend" + selectedChat+".txt";

        // Create a File object
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        String c = enterChat.getText();
        if (c.isEmpty()) {
            return;
        }

        String message = HelloController.usernamecopy + ": " + c;

        // Check if the file exists
        if (file1.exists() && file2.exists()) {
            appendMessageToFile(file1, message);
            appendMessageToFile(file2, message);
            appendMessageToChatMaterial(message);
        }
    }

    private void appendMessageToFile(File file, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void appendMessageToChatMaterial(String message) {
        Text text = new Text(message + "\n");
        chatMaterial.getChildren().add(text);
        enterChat.clear();
    }

    private void loadChatHistory() {
        chatMaterial.getChildren().clear();
        String filePath = "E:\\oop project\\src\\main\\java\\com\\example\\demo\\"+selectedChat + "friend" + HelloController.usernamecopy+".txt";
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    appendMessageToChatMaterial(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void gotoChatBot(ActionEvent event) throws IOException {
        super.switchToChatBot(event);
    }
}
