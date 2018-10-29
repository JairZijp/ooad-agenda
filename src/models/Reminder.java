/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author jairz
 */
public class Reminder extends Appointment {
    
    private int reminderMinutesBefore;
    private String category;

    /**
     *
     * @param name
     * @param date
     * @param time
     * @param reminderMinutesBefore
     * @param category
     */
    public Reminder(String name, String date, String time, int reminderMinutesBefore, String category) {
        super(name, date, time);
        this.reminderMinutesBefore = reminderMinutesBefore;
        this.category = category;
    }
    
    public void getReminders() {
        
    }
    
    /**
     *
     * @throws SQLException
     */
    public void addReminder() throws SQLException, ParseException {
        
        DB Connection = new DB();
        
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time timeType = new Time(formatter.parse(time).getTime());
        
        String query = String.format(
                "INSERT INTO appointment(name, date, time) VALUES('%s', '%s', '%s'); " +
                "SET @id_val = (SELECT LAST_INSERT_ID()); " +
                "INSERT INTO reminder(appointment_id, reminder_minutes_before, category) " +
                "VALUES(@id_val, '%s', '%s')"
                
        , name, date, timeType, reminderMinutesBefore, category);
        
        //execute query and close connection
        Connection.executeUpdateQuery(query);
        Connection.close();
        
    }
    
    
    
}
