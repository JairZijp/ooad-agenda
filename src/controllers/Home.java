package controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private Calendar calendar;
    private final SimpleDateFormat format = new SimpleDateFormat("d LLLL");
    private Date date = new Date();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get new instance
        calendar = Calendar.getInstance();
        // display dates
        updateFxml();
    }
    
    @FXML
    public void NextMonth(ActionEvent event){
        calendar.add(Calendar.MONTH, 0);
        updateFxml();
    }
    @FXML
    public void PrevMonth(ActionEvent events){
        calendar.add(Calendar.MONTH, -2);
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
        
        while(month == calendar.get(Calendar.MONTH)){
            System.out.println(calendar.getTime());
            DaysOfYear.getItems().add(format.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    @FXML
    private void NewAppointmentScreen() throws IOException {
        Main.GoToScreen("NewAppointment.fxml");
    }
    
}
