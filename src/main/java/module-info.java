module com.example.javafxlogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.javafxlogin to javafx.fxml;
    exports com.example.javafxlogin;
    exports com.example.javafxlogin.controller;
    opens com.example.javafxlogin.controller to javafx.fxml;
}