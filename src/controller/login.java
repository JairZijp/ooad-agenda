package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class login {

    @FXML
    private void NextScreen(ActionEvent event) throws IOException {
        Main.GoToScreen("home.fxml");
    }
}
