/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.RecurringAppointment;
import models.Reminder;
import models.SingleAppointment;

/**
 * FXML Controller class
 *
 * @author jairz
 */
public class Appointment implements Initializable {

    
    /* Single appointment */
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
    
    /* Reminder */
    @FXML
    private TextField reminderMinutesBefore;
    
    /* Recurring */
    @FXML
    private TextField frequencyField; 
    @FXML
    private TextField timesTotalField;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Appointment() {
        
    }
    
    /**
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    public void addSingleAppointment(ActionEvent event) throws SQLException {
        
        String name = nameField.textProperty().get(),
                description = descriptionField.textProperty().get(),
                time = startTimeField.textProperty().get(),
                endTime = endTimeField.textProperty().get(),
                startTime = startTimeField.textProperty().get(),
                location = locationField.textProperty().get();
        
        String startDate = startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                endDate = endDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        SingleAppointment a = new SingleAppointment(location, endDate, endTime, description, startDate, name, endDate, time);
        
        a.addSingleAppointment();
    
    }
    
    /**
     *
     * @param event
     * @throws SQLException
     */
    public void addReminder(ActionEvent event) throws SQLException {
      
        String name = nameField.textProperty().get(),
            time = startTimeField.textProperty().get(),
            date = startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            category = categoryField.textProperty().get();
        
        int minutesBefore = Integer.parseInt(reminderMinutesBefore.textProperty().get());
        
        Reminder r = new Reminder(name, date, time, minutesBefore, category);
        
        r.addReminder();
        
    }
    
    public void addRecurringAppointment(ActionEvent event) throws SQLException {
      
        String name = nameField.textProperty().get(),
                description = descriptionField.textProperty().get(),
                time = startTimeField.textProperty().get(),
                endTime = endTimeField.textProperty().get(),
                location = locationField.textProperty().get();
            
        int timesTotal = Integer.parseInt(timesTotalField.textProperty().get()),
                frequency =  Integer.parseInt(frequencyField.textProperty().get());
        
        String startDate = startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                endDate = endDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        RecurringAppointment r = new RecurringAppointment(location, description, 
                endDate, endTime, startDate, time, frequency, timesTotal, name, startDate, time);
        
        r.addRecurringAppointment();
        
    }
    
    @FXML
    private void NewAppointmentScreen() throws IOException {
        Main.GoToScreen("NewAppointment.fxml");
    }
    
    @FXML
    private void NewReminderScreen() throws IOException {
        Main.GoToScreen("NewReminder.fxml");
    }
    
    @FXML
    private void NewRecurringScreen() throws IOException {
        Main.GoToScreen("NewRecurring.fxml");
    }
    
}
