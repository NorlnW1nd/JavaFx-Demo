package com.example.javafxlogin.controller;

import com.example.javafxlogin.utils.DatabasesConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    public void registerButtonOnAction(ActionEvent event){
        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            registrationMessageLabel.setText("User has been registered successfully!");
            registerUser();
        }else {
            confirmPasswordLabel.setText("Password does not match");
        }
    }

    public void registerUser(){
        DatabasesConnection connectNow = new DatabasesConnection();
        Connection connectDb = connectNow.getConnection();

        String insertSql = "insert into user(password,username,enabled) values('"+setPasswordField.getText()+"','"+usernameTextField.getText()+"','1')";

        try {
            PreparedStatement preparedStatement = connectDb.prepareStatement(insertSql);
            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}