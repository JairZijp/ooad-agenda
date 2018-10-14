package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Appointment;
import models.DB;
import models.Duration;
import models.User;

public class SingleAppointment extends Appointment implements Duration {
    
    private String location;
    private LocalDate endDate;
    private String endTime;
    private String description;
    private LocalDate startDate;

    public SingleAppointment(String location, LocalDate endDate, String endTime, String description, LocalDate startDate, String name, LocalDate date, String time, String category) {
        super(name, date, time, category);
        this.location = location;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
        this.startDate = startDate;
    }

    public void hasDuration() {
        // do something
    }

    public void getAppointments() {
        
    }
    
    public void addAppointment() throws SQLException {
        
         //create connection and execute query
        DB Connection = new DB();

        //this query will only select 1 row if the data is correct
        String sql = String.format("INSERT INTO single_appointment(name, date, time, category, location, endDate, endTime, description, startDate, startTime)"
                + "                 VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                                    name, date, time, category, location, endDate, endTime, description, startDate, time
                );
        
        //execute query and close connection
        Connection.executeUpdateQuery(sql);
        Connection.close();
        
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
   
}
