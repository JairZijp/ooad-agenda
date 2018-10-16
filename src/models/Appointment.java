/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author jairz
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
