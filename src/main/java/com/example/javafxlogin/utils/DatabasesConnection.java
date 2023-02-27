package com.example.javafxlogin.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabasesConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "yourdatabase";
        String databaseUser = "yourusername";
        String databasePassword = "yourpassword";
        String url = "jdbc:mysql://localhost:3306/"+databaseName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);


        }catch (Exception e){

        }
        return databaseLink;
    }
}
