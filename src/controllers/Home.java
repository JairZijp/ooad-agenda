package controllers;

import static controllers.Main.getCurrentUser;
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
import java.io.IOException;

/**
 *  Home Controller
 * 
 * @author Simon Wiering
 */
public class Home implements Initializable {
    
    @FXML
    private ListView DaysOfYear;
    @FXML
    private Label YearLabel;

    private final String YEAR_LABEL_PREFIX = "Huidige jaar: ";    
    private final SimpleDateFormat format = new SimpleDateFormat("d LLLL");
    
    private Calendar calendar;
    
    private ArrayList<String> monthNames;
    private ArrayList<String> monthDates;
    private ArrayList<String> monthTimes;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get new instance
        calendar = Calendar.getInstance();
        
        try {
            getAppointmentStarts();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // display dates
        updateFxml();
    }
    
    @FXML
    public void NextMonth(ActionEvent event) throws SQLException, ParseException{
        calendar.add(Calendar.MONTH, 0);
        getAppointmentStarts();
        updateFxml();
    }
    @FXML
    public void PrevMonth(ActionEvent events) throws SQLException, ParseException{
        calendar.add(Calendar.MONTH, -2);
        getAppointmentStarts();
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
        int appCount;
        String dayAppointments;
        
        while(month == calendar.get(Calendar.MONTH)){
            appCount = 0;
            dayAppointments = "";
            
            for(int i = 0; i < monthDates.size(); i++){
                if(monthDates.get(i).equals(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)))){
                    dayAppointments = String.format(
                            "%s %s %s |",
                            dayAppointments, monthNames.get(i), monthTimes.get(i)
                            );
                }
            }
//            String countString = appCount > 0 ? "  \t" + appCount : " ";

            DaysOfYear.getItems().add(format.format(calendar.getTime()) + dayAppointments);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    private void getAppointmentStarts() throws SQLException, ParseException {
        // new database connection
        DB connection = new DB();
        
        monthNames = new ArrayList<>();
        monthDates = new ArrayList<>();
        monthTimes = new ArrayList<>();
        
        // get month and year
        int month = calendar.get(Calendar.MONTH) + 1;        
        int year = calendar.get(Calendar.YEAR);
        // get appointments in this year/month combination
        String query = String.format(
                "SELECT * FROM appointment WHERE date LIKE '%s-%s-%%' AND userId = %s",
                year, month, getCurrentUser());
        System.out.println(query);
        
        // execute quer
        ResultSet monthAppointments = connection.executeResultSetQuery(query);
        
        // initiate new arraylist to add all the days.
        while(monthAppointments.next()){
            String currentDate = monthAppointments.getString("date");
            String currentName = monthAppointments.getString("name");
            String currentTime = monthAppointments.getString("time");
            
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            Date theDate = day.parse(currentDate);
            
            Calendar thisDay = Calendar.getInstance();
            thisDay.setTime(theDate);
            
            String currentDay = Integer.toString(thisDay.get(Calendar.DAY_OF_MONTH));
            
            monthDates.add(currentDay);
            monthNames.add(currentName);
            monthTimes.add(currentTime);            
        }
    }
    @FXML
    private void NewAppointmentScreen() throws IOException {
        Main.GoToScreen("NewAppointment.fxml");
    }
}
