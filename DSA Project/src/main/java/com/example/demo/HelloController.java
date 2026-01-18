package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class HelloController {
    private String password1;
    private String username1;
    MyArrayList<String> listofusers1 = new MyArrayList<>();
    public static String usernamecopy;

    @FXML
    private Stage stage1;
    @FXML
    private Scene scene1 ;
    @FXML
    private Parent root1;
    @FXML
    private Button loginButton1;
    @FXML
    private Button signupButton1;
    @FXML
    private TextField uname1;
    @FXML
    private PasswordField pword1;
    @FXML
    private Hyperlink fpword1;
    @FXML
    private ImageView userImage1;
    @FXML
    private AnchorPane layer;
    @FXML
    private HBox userHB;
    @FXML
    private HBox passHB;



    /////Signup Setup Variables/////

    private String username;
    private String email;
    private long contactnumber;
    private String password;
    private String conpassword;
    private String hint;

    @FXML
    private TextField uname;
    @FXML
    private TextField mail;
    @FXML
    private TextField num;
    @FXML
    private PasswordField pword;
    @FXML
    private PasswordField cpword;
    @FXML
    private TextField hnt;
    @FXML
    private Button submt;
    @FXML
    private Button back;
    @FXML
    private ImageView backImage;

    @FXML
    private HBox hb1;
    @FXML
    private HBox hb2;
    @FXML
    private HBox hb3;
    @FXML
    private HBox hb4;
    @FXML
    private HBox hb5;
    @FXML
    private HBox hb6;

    ////////////////////////////////


    @FXML
    public void initialize(){

        hb1.setVisible(false);
        hb2.setVisible(false);
        hb3.setVisible(false);
        hb4.setVisible(false);
        hb5.setVisible(false);
        hb6.setVisible(false);
        submt.setVisible(false);
        back.setVisible(false);
        backImage.setVisible(false);

        userImage1.setVisible(true);
        fpword1.setVisible(true);
        signupButton1.setVisible(true);
        loginButton1.setVisible(true);
        userHB.setVisible(true);
        passHB.setVisible(true);

    }

    public String getUsername() {return username1;}

    public String getPassword() {return password1;}

    @FXML
    public void setLogin(ActionEvent event) throws IOException {
        username1=uname1.getText();
        password1=pword1.getText();
        usernamecopy=username1;

        File info = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");
        try (BufferedReader reading = new BufferedReader(new FileReader(info))) {

            String line;
            while ((line = reading.readLine()) != null) {
                // Split the line by colon
                String[] parts = line.split(":");
                if (parts.length >= 4) {
                    listofusers1.add(parts[0]);
                }
            }
        }

        if(InfoCheck()){

            Parent  root3 = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene (root3);
            stage.setScene(scene3);
            stage.show();
        }
    }

    public boolean InfoCheck() {
        File info = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");
        String Checker = getUsername() + ":" + getPassword();
        boolean userFound = false;

        try (BufferedReader reading = new BufferedReader(new FileReader(info))) {
            String line;
            while ((line = reading.readLine()) != null) {
                // Split the line by colon
                String[] parts = line.split(":");
                if (parts.length >= 4) {
                    String usernamePart = parts[0];
                    String passwordPart = parts[1];

                    if (usernamePart.equals(getUsername())) {
                        userFound = true;
                        if (passwordPart.equals(getPassword())) {
                            return true;  // Correct username and password
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Alert!");
                            alert.setContentText("Wrong Password");
                            alert.showAndWait();
                            return false;  // Correct username but wrong password
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!userFound) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Wrong Username");
            alert.showAndWait();
        }

        return false;  // Username not found
    }

    @FXML
    private void switchToSignup(ActionEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.8),layer);
        slide.setToX(565);

        hb1.setVisible(true);
        hb2.setVisible(true);
        hb3.setVisible(true);
        hb4.setVisible(true);
        hb5.setVisible(true);
        hb6.setVisible(true);
        submt.setVisible(true);
        back.setVisible(true);
        backImage.setVisible(true);

        userImage1.setVisible(false);
        fpword1.setVisible(false);
        signupButton1.setVisible(false);
        loginButton1.setVisible(false);
        userHB.setVisible(false);
        passHB.setVisible(false);

        slide.setOnFinished(e -> {
            layer.setTranslateX(565); // Reset position for reverse transition
        });

        slide.play();
    }

    @FXML
    private void switchToForgotPassword(ActionEvent event)throws IOException {
        Parent  root3 = FXMLLoader.load(getClass().getResource("forgotpassword.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene3 = new Scene (root3);
        stage.setScene(scene3);
        stage.show();
    }

          ////////////// Signup Setup////////////////////


    public String getHint() {
        return hint;
    }

    @FXML
    private boolean setSignUp() throws IOException {
        username = uname.getText();
        email = mail.getText();

        try {
            contactnumber = Integer.parseInt(num.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Please enter a valid contact number.");
            alert.showAndWait();
            return false;
        }

        password = pword.getText();
        conpassword = cpword.getText();
        hint = hnt.getText();

        if (!password.equals(conpassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Your Password doesn't match with your Confirm Password.");
            alert.showAndWait();
            return false;
        } else {
            if (checkUsername() && checkHint()) {
                File file = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");
                File file1 = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\" + username + "friendlist.txt");

                try (BufferedWriter writing = new BufferedWriter(new FileWriter(file, true));
                     BufferedWriter writing1 = new BufferedWriter(new FileWriter(file1, true))) {

                    writing.write(username + ":" + password + ":" + hint + ":" + email + ":" + contactnumber);
                    writing.newLine();
                    writing1.write("--Friend Request's--");
                    writing.close();
                    writing1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkHint() throws IOException {
        File file3 = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");

        try (BufferedReader reading = new BufferedReader(new FileReader(file3))) {
            String line;
            while ((line = reading.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 2 && parts[2].equals(hint)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert!");
                    alert.setContentText("Your hint matches with someone else. Try a new one.");
                    alert.showAndWait();
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean checkUsername() throws IOException {
        File file3 = new File("E:\\oop project\\src\\main\\java\\com\\example\\demo\\info.txt");

        try (BufferedReader reading = new BufferedReader(new FileReader(file3))) {
            String line;
            while ((line = reading.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert!");
                    alert.setContentText("Your Username matches with someone else. Try a new one.");
                    alert.showAndWait();
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @FXML
    private void switchToHello(ActionEvent event) throws IOException {
        if (setSignUp()) {

            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.8),layer);
            slide.setToX(0);

            hb1.setVisible(false);
            hb2.setVisible(false);
            hb3.setVisible(false);
            hb4.setVisible(false);
            hb5.setVisible(false);
            hb6.setVisible(false);
            submt.setVisible(false);
            back.setVisible(false);
            backImage.setVisible(false);


            fpword1.setVisible(true);
            signupButton1.setVisible(true);
            loginButton1.setVisible(true);
            userHB.setVisible(true);
            passHB.setVisible(true);

            slide.setOnFinished(e -> {
                layer.setTranslateX(0); // Reset position for reverse transition
                userImage1.setVisible(true);
            });

            slide.play();
        }
    }
    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.8),layer);
        slide.setToX(0);

        hb1.setVisible(false);
        hb2.setVisible(false);
        hb3.setVisible(false);
        hb4.setVisible(false);
        hb5.setVisible(false);
        hb6.setVisible(false);
        submt.setVisible(false);
        back.setVisible(false);
        backImage.setVisible(false);


        fpword1.setVisible(true);
        signupButton1.setVisible(true);
        loginButton1.setVisible(true);
        userHB.setVisible(true);
        passHB.setVisible(true);

        slide.setOnFinished(e -> {
            layer.setTranslateX(0); // Reset position for reverse transition
            userImage1.setVisible(true);
        });

        slide.play();

    }

}
