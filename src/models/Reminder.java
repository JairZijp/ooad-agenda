package models;

import controllers.Main;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Reminder appointment (subclass of Appointment)
 * 
 * @author Jaïr Zijp
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
     * @throws java.text.ParseException
     */
    public void addReminder() throws SQLException, ParseException {
        
        DB Connection = new DB();
        
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time timeType = new Time(formatter.parse(time).getTime());
        
         // get current user id
        int userId = Main.getCurrentUser();
        
        String query = String.format(
                "INSERT INTO appointment(user_id, name, date, time) VALUES('%s', %s', '%s', '%s'); " +
                "SET @id_val = (SELECT LAST_INSERT_ID()); " +
                "INSERT INTO reminder(appointment_id, reminder_minutes_before, category) " +
                "VALUES(@id_val, '%s', '%s')"
                
        , userId, name, date, timeType, reminderMinutesBefore, category);
        
        //execute query and close connection
        Connection.executeUpdateQuery(query);
        Connection.close();
        
    }
    
    
    
}
