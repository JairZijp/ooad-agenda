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

/**
 * Login controller
 * 
 * @author Simon Wiering
 */
public class Login {

    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;

    // Function to change to navigate to another FXML    
    @FXML
    private void NextScreen(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {       

        // set username and password from textfields
        String username = UsernameField.textProperty().get(),
                password = PasswordField.textProperty().get(),
                hashedPassword = User.hashPassword(password);

        
        Auth auth = Auth.getInstance();
        
        boolean validUser = auth.validate(username, hashedPassword);
        
        if (validUser) {

            //Navigate back to old screen
            Main.GoToScreen("Home.fxml");
        }

    }
    
}
