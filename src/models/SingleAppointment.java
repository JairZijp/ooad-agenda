package main.java.models;

import java.sql.Date;
import java.sql.Time;
import models.Appointment;
import models.Duration;

public class SingleAppointment extends Appointment implements Duration {
    
    private String location;
    private Date endDate;
    private Time endTime;
    private String description;
    private Date startDate;
    private Time startTime;

    public SingleAppointment(String location, Date endDate, Time endTime, String description) {
        this.location = location;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
   
}
