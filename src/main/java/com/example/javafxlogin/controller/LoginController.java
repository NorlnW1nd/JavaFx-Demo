package com.example.javafxlogin.controller;

import com.example.javafxlogin.HelloApplication;
import com.example.javafxlogin.entity.User;
import com.example.javafxlogin.mapper.UserMapper;
import com.example.javafxlogin.utils.DatabasesConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginController {

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Label loginMessageLable;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;


    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {

        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();

        }else{
            loginMessageLable.setText("Please enter username and password");
        }
    }

    @FXML
    void registerButtonOnClick(ActionEvent event) {
        createAccountForm();
    }

    public void validateLogin(){
        DatabasesConnection connectNow = new DatabasesConnection();
        Connection connectDb = connectNow.getConnection();

        String verifylogin = "select count(*) from user where username = '"+usernameTextField.getText()+"' and password = '"+enterPasswordField.getText()+"'";

        try {
            PreparedStatement preparedStatement = connectDb.prepareStatement(verifylogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getInt(1) == 1){
                    loginMessageLable.setText("Congrats! you are in!");
                }else {
                    loginMessageLable.setText("Please try again");
                }
            }
            resultSet.close();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 420);
            Stage registerStage = new Stage();
            registerStage.setTitle("Login!");
            registerStage.setScene(scene);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}