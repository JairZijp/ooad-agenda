/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.DB;
import models.User;

import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;

    @FXML
    private void NextScreen(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
        // create new User
        User user = new User();

        // set username and password from textfields
        String username = UsernameField.textProperty().get(),
                password = PasswordField.textProperty().get(),
                hashedPassword = User.HashPassword(password);

        //create connection and execute query
        DB Connection = new DB();

        //this query will only select 1 row if the data is correct
        String sql = String.format("SELECT * FROM user WHERE `username` = '%s' AND `password` = '%s'",
                username, hashedPassword);

        ResultSet queryResult = Connection.executeResultSetQuery(sql);

        //get the first row from the returned data and set User-properties
        if (queryResult.first()) {

            //set properties of current User
            user.setUsername(queryResult.getString("username"));

            //Navigate back to old screen
            Main.GoToScreen("Home.fxml");
        }
//        else {
//            ErrorLabel.setText("Incorrect username/password");
//        }
    }
}
