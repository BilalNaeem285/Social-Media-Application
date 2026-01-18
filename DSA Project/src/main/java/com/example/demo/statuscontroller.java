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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.nio.file.Paths;


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class statuscontroller extends homepagecontroller {
    @FXML
    private Button addButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listview1;
    @FXML
    private Label l1;
    @FXML
    private Button nextImageButton;
    @FXML
    private Button previousImageButton;
    @FXML
    private ImageView i1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Button showMyStatus;

    private FileChooser fileChooser = new FileChooser();
    private List<File> files = new ArrayList<>();
    private List<String> userStatusList = new ArrayList<>();
    private int currentFileIndex = -1; // No file loaded initially
    private String selectedPersonStatus;
    ArrayList<String> fileLocations = new ArrayList<>();

    @FXML
    private void showMyFriendStatus(ActionEvent event)throws IOException{
        currentFileIndex = -1;
        fileLocations.clear(); // Clear the list before populating it again
        files.clear(); // Clear the files list to avoid duplicates

        // Constructing the file path
        String filePath = "E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + selectedPersonStatus+ "status.txt";
        File file1 = new File(filePath);

        try (BufferedReader reading = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = reading.readLine()) != null) {
                fileLocations.add(line.trim()); // Trim whitespace from the line before adding
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately, maybe display an error message
        }

        // Populate the files list
        for (String path : fileLocations) {
            File file = new File(path);
            if (file.exists()) {
                files.add(file);
            }
        }

        // Display the first file if available
        if (!files.isEmpty()) {
            currentFileIndex = 0;
            displayFile(files.get(currentFileIndex));
            updateLabel();
            updateButtons();
        }
    }


    @FXML
    private void showMyStatus1(ActionEvent event) {
        currentFileIndex = -1;
        fileLocations.clear(); // Clear the list before populating it again
        files.clear(); // Clear the files list to avoid duplicates

        // Constructing the file path
        String filePath = "E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + HelloController.usernamecopy + "status.txt";
        File file1 = new File(filePath);

        try (BufferedReader reading = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = reading.readLine()) != null) {
                fileLocations.add(line.trim()); // Trim whitespace from the line before adding
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately, maybe display an error message
        }

        // Populate the files list
        for (String path : fileLocations) {
            File file = new File(path);
            if (file.exists()) {
                files.add(file);
            }
        }

        // Display the first file if available
        if (!files.isEmpty()) {
            currentFileIndex = 0;
            displayFile(files.get(currentFileIndex));
            updateLabel();
            updateButtons();
        }
    }

    @FXML
    void searchInStatus(ActionEvent event){
        super.search(event);
    }

    void initialize() {
        // Disable the buttons initially if there are no files
        updateButtons();
    }
    @FXML
    void showFriendStatusList(ActionEvent event) throws IOException {
        // Construct the file path
        String filePath = "E:\\oop project\\src\\main\\java\\com\\example\\demo\\"
                + HelloController.usernamecopy
                + "friendlist.txt";

        File file = new File(filePath);
        userStatusList.clear(); // Clear the list to avoid duplicates
        try (BufferedReader reading = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reading.readLine()) != null) {
                if(line.equals("--Friend Request's--")){
                    break;
                }
                userStatusList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initially display all users in the ListView
        updateListView(userStatusList);

        // Add a listener to capture the selected item
        listview1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedPersonStatus = newValue;
            System.out.println("Selected person status: " + selectedPersonStatus);
        });

        // Add a listener to the search bar to filter the list based on input
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            List<String> filteredList = searchList(newValue, userStatusList);
            updateListView(filteredList);
        });

        // No need to print selectedPersonStatus here because it will likely be null at this point
        // It will be printed when the selection changes, thanks to the listener
    }


    private void updateListView(List<String> list) {
        listview1.getItems().clear();
        listview1.getItems().addAll(list);
    }

    void addFileListstoFiling() {
        try {
            File file = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + HelloController.usernamecopy + "status.txt");
            FileWriter b = new FileWriter(file, false);
            BufferedWriter writing = new BufferedWriter(b);

            // Iterate over the file list and write each file path to the file
            for (File fileEntry : files) {
                writing.write(fileEntry.getAbsolutePath());
                writing.newLine();
            }

            // Close the writer to flush and release resources
            writing.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getFile(ActionEvent event) {
        // Open FileChooser and set its title and allowed extensions
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        // If a file is selected, add its path to the list and display it
        if (file != null) {
            files.add(file);
            addFileListstoFiling();
            currentFileIndex = files.size() - 1;
            displayFile(files.get(currentFileIndex));
            updateLabel();
            updateButtons();
        }
    }

    @FXML
    void nextFile(ActionEvent event) {
        if (currentFileIndex < files.size() - 1) {
            currentFileIndex++;
            displayFile(files.get(currentFileIndex));
            updateLabel();
            updateButtons();
        }
    }

    @FXML
    void previousFile(ActionEvent event) {
        if (currentFileIndex > 0) {
            currentFileIndex--;
            displayFile(files.get(currentFileIndex));
            updateLabel();
            updateButtons();
        }
    }

    @FXML
    private void displayFile(File file) {
        try {
            if (isImageFile(file)) {
                Image image = new Image(file.toURI().toString());
                i1.setImage(image);
                l2.setText(""); // Clear the text label
            } else if (isTextFile(file)) {
                i1.setImage(null); // Clear the image view
                String content = new String(Files.readAllBytes(file.toPath()));
                l2.setText(content);
            }
        } catch (Exception e) {
            l2.setText("Unable to display file: " + e.getMessage());
            e.printStackTrace(); // Log the error to the terminal for debugging
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg");
    }

    private boolean isTextFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".txt");
    }

    private void updateLabel() {
        l1.setText("File: " + (currentFileIndex + 1) + " / " + files.size());
    }

    private void updateButtons() {
        previousImageButton.setDisable(currentFileIndex <= 0);
        nextImageButton.setDisable(currentFileIndex >= files.size() - 1);
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
    private void gotoChatBot(ActionEvent event) throws IOException {
        super.switchToChatBot(event);
    }
}