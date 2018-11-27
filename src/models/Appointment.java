package models;

import static controllers.Main.getCurrentUser;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Appointment class
 * 
 * Base appointment class. Used to define the basic properties of an appointment.
 * 
 * @author Jaïr Zijp
 */
public abstract class Appointment {
    
    public String name;
    public String date;
    public String time;

    public Appointment(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }
    
    public void setAppointment() {
        
         //Make Connection
        DB Connection = new DB();
        String sql = String.format("INSERT INTO appointment (name, date, time)" +
                        "VALUES ('%s', '%s', '%s')",
                this.name, this.date, this.time);

        //execute query and close connection
        Connection.executeUpdateQuery(sql);
        Connection.close();
        
    }
    
    
    public ResultSet getAppointments() throws SQLException {
 
        DB connection = new DB();
        
        String query = String.format("SELECT * FROM appointment");
        System.out.println(query);
        
        ResultSet list = connection.executeResultSetQuery(query);;
        
        return list;    
    }
    
    /* getter and setters */

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
