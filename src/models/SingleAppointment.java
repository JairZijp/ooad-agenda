package models;

import controllers.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.Appointment;
import models.DB;
import models.Duration;

/**
 * Single appointment (subclass of Appointment)
 * 
 * @author Jaïr Zijp
 */
public class SingleAppointment extends Appointment implements Duration {
    
    private String location;
    private String endDate;
    private String endTime;
    private String description;
    private String startDate;
    private String startTime;

    /**
     *
     * @param location
     * @param endDate
     * @param endTime
     * @param description
     * @param startDate
     * @param startTime
     * @param name
     * @param date
     * @param time
     */
    public SingleAppointment(String location, String endDate, String endTime, String description, String startDate, String startTime, String name, String date, String time) {
        super(name, date, time);
        this.location = location;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
    }    

    public void hasDuration() {
        // do something
    }
    
   
    /**
     *
     * @throws SQLException
     * @throws ParseException
     */
    public void addSingleAppointment() throws SQLException, ParseException {
        
         //create connection and execute query
        DB Connection = new DB();

        // get current user id
        int userId = Main.getCurrentUser();
        
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time startTimeType = new Time(formatter.parse(startTime).getTime());
        Time endTimeType = new Time(formatter.parse(endTime).getTime());
             
        String query = String.format(
                                          "INSERT INTO appointment(user_id, name, date, time) VALUES('%s', '%s', '%s', '%s'); " 
                                        + "SET @id_val = (SELECT LAST_INSERT_ID()); "
                                        + "INSERT INTO single_appointment(appointment_id, location, end_date, end_time, description, start_date, start_time) "
                                        + "VALUES(@id_val, '%s', '%s', '%s', '%s', '%s', '%s') "    
                                    , userId, name, date, startTimeType, location, endDate, endTimeType, description, startDate, startTimeType);

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
   
}
