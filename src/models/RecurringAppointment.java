/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author jairz
 */
public class RecurringAppointment extends Appointment implements Recurring {
    
    private String location;
    private String description;
    private LocalDate endDate;
    private Time endTime;
    private LocalDate startDate;
    private Time startTime;
    private String frequency;
    private int timesTotal;

    public RecurringAppointment(String name, LocalDate date, String time) {
        super(name, date, time);
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
