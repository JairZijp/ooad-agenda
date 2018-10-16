/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author jairz
 */
public class RecurringAppointment extends Appointment implements Recurring {
    
    private String location;
    private String description;
    private String endDate;
    private String endTime;
    private String startDate;
    private String startTime;
    private int frequency;
    private int timesTotal;

    /**
     *
     * @param location
     * @param description
     * @param endDate
     * @param endTime
     * @param startDate
     * @param startTime
     * @param frequency
     * @param timesTotal
     * @param name
     * @param date
     * @param time
     */
    public RecurringAppointment(String location, String description, String endDate, String endTime, String startDate, String startTime, int frequency, int timesTotal, String name, String date, String time) {
        super(name, date, time);
        this.location = location;
        this.description = description;
        this.endDate = endDate;
        this.endTime = endTime;
        this.startDate = startDate;
        this.startTime = startTime;
        this.frequency = frequency;
        this.timesTotal = timesTotal;
    }
    
    /**
     *
     * @throws SQLException
     */
    public void addRecurringAppointment() throws SQLException {
        
        DB Connection = new DB();
        
        String query = String.format(
                "INSERT INTO appointment(name, date, time) VALUES('%s', '%s', '%s'); " +
                "SET @id_val = (SELECT LAST_INSERT_ID()); " +
                "INSERT INTO recurring_appointment(appointment_id, reminder_minutes_before, category) " +
                "VALUES(@id_val, '%s', '%s', '%s', '%s', '%s', '%s', '%s')"
                
        , name, date, time, description, endDate, endTime, startDate, startTime, frequency, timesTotal);
        
        //execute query and close connection
        Connection.executeUpdateQuery(query);
        Connection.close();
        
        
    }

    public void isRecurring() {
        // do something
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   

}
