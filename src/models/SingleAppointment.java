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

    public SingleAppointment(String location, LocalDate endDate, String endTime, String description, LocalDate startDate, String name, LocalDate date, String time) {
        super(name, date, time);
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
             
        String query = String.format(""
                                        + "INSERT INTO appointment(name, date, time) VALUES('%s', '%s', '%s'); "
                                        + "SET @id_val = (SELECT LAST_INSERT_ID()); "
                                        + "INSERT INTO single_appointment(appointment_id, location, end_date, end_time, description, start_date, start_time) "
                                        + "VALUES(@id_val, '%s', '%s', '%s', '%s', '%s', '%s'); "
                                    , name, date, time, location, endDate, endTime, description, startDate, time);

        //execute query and close connection
        Connection.executeUpdateQuery(query);
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
