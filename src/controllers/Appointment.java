/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.SingleAppointment;

/**
 * FXML Controller class
 *
 * @author jairz
 */
public class Appointment implements Initializable {

    
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField categoryField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Appointment() {
        
    }
    
    @FXML
    public void addSingleAppointment(ActionEvent event) throws SQLException {
        
        String name = nameField.textProperty().get(),
                description = descriptionField.textProperty().get(),
                time = startTimeField.textProperty().get(),
                endTime = endTimeField.textProperty().get(),
                location = locationField.textProperty().get(),
                category = categoryField.textProperty().get();
        
        LocalDate startDate = startDateField.getValue(),
                endDate = endDateField.getValue();
        
        SingleAppointment a = new SingleAppointment(location, endDate, endTime, description, startDate, name, endDate, time);
        
        a.addAppointment();
       
        
    }
    
}
