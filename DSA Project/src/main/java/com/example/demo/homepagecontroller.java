package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import java.util.stream.Collectors;

public class homepagecontroller extends AbstractOfHomepage{
    @FXML
    private Button b1;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField searchRequestBar;
    @FXML
    private Button searchRequestButton;
    @FXML
    private ListView<String> listViewRequest;
    @FXML
    private AnchorPane requestPane;

    @FXML
    private ListView<String> listview1;
    protected ArrayList<String> userRequestList = new ArrayList<>();
    protected ArrayList<String> userlists = new ArrayList<>();
    protected ArrayList<String> friendlist = new ArrayList<>();
    protected static String selectedItem;
    protected String selectedRequestItem;
    public int n=0;

    @FXML
    public void requestSection(){
        requestPane.setVisible(false);
    }

    @FXML
    protected void signOutMethod(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert!");
        alert.setContentText("Do you want to Sign Out?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            Parent root2 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene2 = new Scene (root2);
            stage.setScene(scene2);
            stage.show();

        } else if (result.isPresent() && result.get() == noButton) {

        }

    }

    protected void getUserlists() {
        try {
            File friendlistfile = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + HelloController.usernamecopy + "friendlist.txt");
            FileReader friendreader = new FileReader(friendlistfile);
            BufferedReader friendreading = new BufferedReader(friendreader);

            String line;
            while((line= friendreading.readLine()) !=null){
                if(line.equals("--Friend Request's--")){
                    break;
                }
                friendlist.add(line);
            }


            File file = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");
            FileReader b = new FileReader(file);
            BufferedReader reading = new BufferedReader(b);

            userlists.clear();

            // Iterate over the file list and write each file path to the file
            line=null;
            boolean found=true;

            while ((line = reading.readLine()) != null ) {
                String[] parts = line.split(":");
                if (parts.length >= 4 && !parts[0].equals(HelloController.usernamecopy)) {

                    for(int i=0;i<friendlist.size();i++){
                        if(parts[0].equals(friendlist.get(i))){
                            found=false;
                            break;
                        }
                    }

                    if(found){
                    userlists.add(parts[0]);
                    }

                }
            }

            // Close the writer to flush and release resources
            reading.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void search(ActionEvent event) {
        getUserlists();
        if(userlists.isEmpty()){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Alert!");
            alert1.setContentText("Every user is already your friend");
            alert1.showAndWait();
        }

        listview1.getItems().clear();
        listview1.getItems().addAll(searchList(searchBar.getText(), userlists));

        // Add a listener to capture the selected item
        listview1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedItem = newValue;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert!");
            alert.setContentText("Do you want to send a Friend Request to '"+selectedItem+"' ?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                File file = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + selectedItem + "friendlist.txt");
                File userFile = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + HelloController.usernamecopy + "friendlist.txt");
                try {

                    BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    BufferedReader reader1 = new BufferedReader(new FileReader(userFile));
                    String line;
                    boolean mainCheck=true;

                    while((line= reader1.readLine())!=null){
                        if(line.equals("--Friend Request's--")){
                            while((line= reader1.readLine())!=null){
                                if(line.equals(selectedItem)){
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setTitle("Alert!");
                                    alert1.setContentText("'"+selectedItem+"' is already in your Request List!");
                                    alert1.showAndWait();
                                    mainCheck=false;
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    boolean check =false;

                    if(mainCheck) {
                        line = null;

                        while ((line = reader.readLine()) != null) {
                            if (line.equals(HelloController.usernamecopy)) {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Alert!");
                                alert1.setContentText("'" + selectedItem + "' is already in your friend list!");
                                alert1.showAndWait();
                                check = true;
                                break;
                            }
                            if (line.equals("--Friend Request's--")) {
                                while ((line = reader.readLine()) != null) {
                                    if (line.equals(HelloController.usernamecopy)) {
                                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                        alert1.setTitle("Alert!");
                                        alert1.setContentText("You have already send your Friend Request to '" + selectedItem + "' !");
                                        alert1.showAndWait();
                                        check = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }

                        if (!check) {
                            writer.write("\n" + HelloController.usernamecopy);
                            writer.close();
                        }
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            } else {
                selectedItem = null;
            }
        });
    }

    protected void getUserRequestList(){

        File userFile = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + HelloController.usernamecopy
                + "friendlist.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(userFile));

            userRequestList.clear();

            String line;
            while((line= reader.readLine())!=null){
                if(line.equals("--Friend Request's--")){
                    while((line= reader.readLine())!=null){
                        userRequestList.add(line);
                    }
                    break;
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void searchRequest(ActionEvent event) {
        if(n==0){
            requestPane.setVisible(true);
            n=1;
        }else{
            requestPane.setVisible(false);
            searchRequestButton.setVisible(true);
            n=0;
        }

        getUserRequestList();
        listViewRequest.getItems().clear();
        listViewRequest.getItems().addAll(searchList(searchRequestBar.getText(),userRequestList));

        // Add a listener to capture the selected item
        listViewRequest.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedRequestItem = newValue;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert!");
            alert.setContentText("Do you want to accept the Friend Request of '"+selectedRequestItem+"' ?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                File userFile = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + HelloController.usernamecopy
                        + "friendlist.txt");
                File friendFile = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + selectedRequestItem
                        + "friendlist.txt");

                try{
                    BufferedReader userReader = new BufferedReader(new FileReader(userFile));
                    BufferedReader friendReader = new BufferedReader(new FileReader(friendFile));

                    ArrayList<String> userTemp = new ArrayList<>();
                    ArrayList<String> friendTemp = new ArrayList<>();

                    String line;

                    while((line= friendReader.readLine())!=null){
                            friendTemp.add(line);
                    }

                    line=null;

                    while((line= userReader.readLine())!=null){
                        if(!line.equals(selectedRequestItem)){
                            userTemp.add(line);
                        }
                    }

                    int index = userTemp.indexOf("--Friend Request's--");
                    if (index != -1) {
                        userTemp.add(index,selectedRequestItem);
                    }

                    index=friendTemp.indexOf("--Friend Request's--");
                    if (index != -1) {
                        friendTemp.add(index,HelloController.usernamecopy);
                    }

                    BufferedWriter writer1 = new BufferedWriter(new FileWriter(userFile));
                    for(int i=0;i<userTemp.size();i++){
                        writer1.write(userTemp.get(i));
                        if(userTemp.size()!=i+1){
                            writer1.newLine();
                        }

                    }
                    writer1.close();

                    BufferedWriter writer2 = new BufferedWriter(new FileWriter(friendFile));
                    for(int i=0;i<friendTemp.size();i++){
                        writer2.write(friendTemp.get(i));
                        if(friendTemp.size()!=i+1){
                            writer2.newLine();
                        }

                    }
                    writer2.close();


                }catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    protected List<String> searchList(String searchWords, List<String> listOfString) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfString.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }


    @FXML
    protected void switchToAbout(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));
        Parent root2 = loader.load();

        aboutcontroller about = loader.getController();
        about.displayInformation();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        stage.setScene(scene2);
        stage.show();
    }
    @FXML
    protected void switchToStatus(ActionEvent event) throws IOException {

        Parent  root2 = FXMLLoader.load(getClass().getResource("status.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene (root2);
        stage.setScene(scene2);
        stage.show();
    }
    @FXML
    protected void switchToChat(ActionEvent event) throws IOException {

        Parent  root2 = FXMLLoader.load(getClass().getResource("chatting.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene (root2);
        stage.setScene(scene2);
        stage.show();
    }
    @FXML
    protected void switchToChatBot(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatBotUI.fxml"));
        Parent root2 = loader.load();

        ChatBotController chatbot = loader.getController();
        chatbot.initial();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        stage.setScene(scene2);
        stage.show();

    }

}
