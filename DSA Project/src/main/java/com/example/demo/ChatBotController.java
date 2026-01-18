package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatBotController extends homepagecontroller{


    @FXML
    private TextArea chatArea;

    @FXML
    private TextField userInputField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button switch1;
    @FXML
    private Button switch2;
    @FXML
    private Button switch3;
    @FXML
    private Button switch4;
    @FXML
    private Button switch5;
    @FXML
    private Button switch6;
    @FXML
    private Button switch7;
    @FXML
    private Button switch8;
    @FXML
    private Button switch9;


    private void getDescription(String message){
        String description = null;
        try {
            // Replace "Java" with your query term
            String searchQuery = message;
            String apiUrl = "https://en.wikipedia.org/api/rest_v1/page/summary/" + searchQuery;

            // Set up connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response to get the description
            JSONObject jsonResponse = new JSONObject(response.toString());
            description = jsonResponse.optString("extract", "No description available.");

        } catch (Exception e) {
            e.printStackTrace();

        }
        if(description==null){
            chatArea.appendText("WikiBot:  No description is available for "+"'" +message+"'");
            scrollToBottom();
            return;
        }
        chatArea.appendText("WikiBot:  " + description + "\n");
        scrollToBottom();
    }

    private String getTitle(){
        String title=null;
        try {
            // URL to fetch a random Wikipedia page summary
            String apiUrl = "https://en.wikipedia.org/api/rest_v1/page/random/summary";
            URL url = new URL(apiUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            title = jsonResponse.getString("title"); // Get the title of the page


        } catch (Exception e) {
            System.out.println("Error fetching random Wikipedia page: " + e.getMessage());
        }
        return title;
    }

    public String formatString(String input) {
        // Split the string into words
        String[] words = input.split(" ");
        StringBuilder formatted = new StringBuilder();

        // Process each word
        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize the first letter and add the rest of the word
                formatted.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase());

                // Add an underscore between words
                formatted.append("_");
            }
        }

        // Remove the trailing underscore if it exists
        if (formatted.length() > 0) {
            formatted.deleteCharAt(formatted.length() - 1);
        }

        return formatted.toString();
    }

    @FXML
    public void sendMessage() {
        String message = userInputField.getText().trim();
        userInputField.clear();
        chatArea.appendText("User:  " + message + "\n");

        if (message.isEmpty()) {
            chatArea.appendText("WikiBot:  I can't understand an empty message.\n");
            scrollToBottom();
            return;
        }

        String formattedString =formatString(message);
        getDescription(formattedString);

    }
    protected void initial(){
        switch4.setVisible(true);
        switch5.setVisible(false);
        switch6.setVisible(false);
        switch7.setVisible(false);
        switch8.setVisible(false);
        switch9.setVisible(false);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();

        switch (clickedButton.getId()) {
            case "switch1":
                chatArea.appendText("User:  To Kill a Mockingbird\n" +
                        "\t Pride and Prejudice\n" +
                        "\t Moby-Dick\n" +
                        "\t Harry Potter\n");
                scrollToBottom();
                break;
            case "switch2":
                chatArea.appendText("User: Artificial Intelligence\n" +
                        "\t Internet of Things\n" +
                        "\t Blockchain\n" +
                        "\t Quantum Computing\n");
                scrollToBottom();
                break;
            case "switch3":
                chatArea.appendText("User: Theory of Relativity\n" +
                        "\t Photosynthesis\n" +
                        "\t Evolution\n" +
                        "\t Big Bang\n");
                scrollToBottom();
                break;
            case "switch4":
                switch4.setVisible(false);
                switch4.setManaged(false);
                switch5.setVisible(true);
                switch6.setVisible(true);
                switch7.setVisible(true);
                switch8.setVisible(true);
                switch9.setVisible(true);
                break;
            case "switch5":
                chatArea.appendText("User: Chess\n" +
                        "\t Grand Theft Auto V\n" +
                        "\t The Legend of Zelda\n" +
                        "\t Fortnite\n");
                scrollToBottom();
                break;
            case "switch6":
                chatArea.appendText("User: The Godfather\n" +
                        "\t Jab Tak Hai Jaan\n" +
                        "\t The Shawshank Redemption\n" +
                        "\t Star Wars\n");
                scrollToBottom();
                break;
            case "switch7":
                chatArea.appendText("User: Ancient Egypt\n" +
                        "\t Industrial Revolution\n" +
                        "\t American Civil War\n" +
                        "\t French Revolution\n");
                scrollToBottom();
                break;
            case "switch8":
                chatArea.appendText("User: Olympic Games\n" +
                        "\t FIFA World Cup\n" +
                        "\t Basketball\n" +
                        "\t Tennis\n");
                scrollToBottom();
                break;
            case "switch9":
                chatArea.appendText("User: Classical Music\n" +
                        "\t Rock Music\n" +
                        "\t Jazz\n" +
                        "\t Hip Hop Music\n");
                scrollToBottom();
                break;

        }
    }



    Scene temp(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChatBotUI.fxml"));
        Scene s2 = new Scene(root);
        return s2;
    }

    private void scrollToBottom() {
        // Scroll to the bottom of the chat area
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }






    @FXML
    private void gotoAbout(ActionEvent event) throws IOException {
        super.switchToAbout(event);
    }
    @FXML
    private void gotoChat(ActionEvent event) throws IOException {
        super.switchToChat(event);
    }
    @FXML
    private void gotoStatus(ActionEvent event) throws IOException {
        super.switchToStatus(event);
    }
    @FXML
    private void gotoSignOut(ActionEvent event) throws IOException {
        super.signOutMethod(event);
    }

}
