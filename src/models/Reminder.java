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
public class Reminder extends Appointment {
    
    private int reminderMinutesBefore;

    public Reminder(String name, LocalDate date, String time) {
        super(name, date, time);
    }
    
    
    
}
