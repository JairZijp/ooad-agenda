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
public class RecurringAppointment extends Appointment implements Recurring {
    
    private String location;
    private String description;
    private Date endDate;
    private Time endTime;
    private Date startDate;
    private Time startTime;
    private String frequency;
    private int timesTotal;

    public RecurringAppointment(String location, String description) {
        this.location = location;
        this.description = description;
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
