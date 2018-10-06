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
import models.Auth;

public class Login {

    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;

    @FXML
    private void NextScreen(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {       

        // set username and password from textfields
        String username = UsernameField.textProperty().get(),
                password = PasswordField.textProperty().get(),
                hashedPassword = User.HashPassword(password);

        
        Auth auth = Auth.getInstance();
        
        boolean validUser = auth.validate(username, hashedPassword);
        
        if (validUser) {

            //Navigate back to old screen
            Main.GoToScreen("Home.fxml");
        }

    }
    
}
