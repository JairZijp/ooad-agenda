/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author jairz
 */
public abstract class Appointment {
    
    private String name;
    private Date date;
    private Time time;
    private String category;

    public Appointment(String name, Date date, Time time, String category) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.category = category;
    }
    
    public void setAppointment() {
        
         //Make Connection
        DB Connection = new DB();
        String sql = String.format("INSERT INTO appointment (name, date, time, category)" +
                        "VALUES ('%s', '%s', '%s', '%s')",
                this.name, this.date, this.time, this.category);

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
   
    
    
}
