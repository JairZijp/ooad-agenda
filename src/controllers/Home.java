/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.DB;

/**
 *
 * @author Simon
 */
public class Home implements Initializable {
    
    @FXML
    private ListView DaysOfYear;
    @FXML
    private Label YearLabel;

    private final String YEAR_LABEL_PREFIX = "Huidige jaar: ";    
    private Calendar calendar;
    private final SimpleDateFormat format = new SimpleDateFormat("d LLLL");
    private Date date = new Date();
    private List<String> appointments;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get new instance
        calendar = Calendar.getInstance();
        
        try {
            appointments = getAppointmentStarts();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // display dates
        updateFxml();
    }
    
    @FXML
    public void NextMonth(ActionEvent event) throws SQLException, ParseException{
        calendar.add(Calendar.MONTH, 0);
        appointments = getAppointmentStarts();
        updateFxml();
    }
    @FXML
    public void PrevMonth(ActionEvent events) throws SQLException, ParseException{
        calendar.add(Calendar.MONTH, -2);
        appointments = getAppointmentStarts();
        updateFxml();
    }
    
    private void updateFxml(){
        setYearLabel();
        displayDates();
    }    
    
    private void setYearLabel(){
        Integer yearOfCalendar = calendar.get(Calendar.YEAR);
        YearLabel.setText(YEAR_LABEL_PREFIX + yearOfCalendar.toString());
    }
    
    private void displayDates(){
        DaysOfYear.getItems().clear();
        // reset date to the first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        // get the month
        int month = calendar.get(Calendar.MONTH);
        int appCount = 0;
        
        while(month == calendar.get(Calendar.MONTH)){
            appCount = 0;
            
            for(int i = 0; i < appointments.size(); i++){
                // comapare if date is same
                // todo: compare dates to each other
                if(appointments.get(i).equals(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)))){
                    appCount++;
                }
            }
            System.out.println(calendar.getTime());
            DaysOfYear.getItems().add(format.format(calendar.getTime()) + "  \t" + appCount);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    private List<String> getAppointmentStarts() throws SQLException, ParseException {
        // new database connection
        DB connection = new DB();
        
        // get month and year
        int month = calendar.get(Calendar.MONTH) + 1;        
        int year = calendar.get(Calendar.YEAR);
        // get appointments in this year/month combination
        String query = String.format(
                "SELECT * FROM appointment WHERE date LIKE '%s-%s-%%'",
                year, month);
        System.out.println(query);
        
        // execute quer
        ResultSet monthAppointments = connection.executeResultSetQuery(query);
        
        // initiate new arraylist to add all the days.
        ArrayList<String> days = new ArrayList<>();
        while(monthAppointments.next()){
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            Date theDate = day.parse(monthAppointments.getString("date"));
            
            Calendar thisDay = Calendar.getInstance();
            thisDay.setTime(theDate);
            
            String currentDay = Integer.toString(thisDay.get(Calendar.DAY_OF_MONTH));
            
            // cast to day since you are in a month
            days.add(currentDay);
        }
        return days;
    }
}
