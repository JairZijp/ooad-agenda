/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author Simon
 */
public class Home {
    
    @FXML
    private void NewAppointmentScreen() throws IOException {
        Main.GoToScreen("NewAppointment.fxml");
    }
    
}
