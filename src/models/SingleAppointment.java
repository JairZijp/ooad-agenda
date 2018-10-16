package models;

import java.sql.SQLException;
import models.Appointment;
import models.DB;
import models.Duration;

public class SingleAppointment extends Appointment implements Duration {
    
    private String location;
    private String endDate;
    private String endTime;
    private String description;
    private String startDate;

    public SingleAppointment(String location, String endDate, String endTime, String description, String startDate, String name, String date, String time) {
        super(name, date, time);
        this.location = location;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
        this.startDate = startDate;
    }

    public void hasDuration() {
        // do something
    }

    public void getAppointments() {
        
    }
    
    public void addSingleAppointment() throws SQLException {
        
         //create connection and execute query
        DB Connection = new DB();
             
        String query = String.format(
                                          "INSERT INTO appointment(name, date, time) VALUES('%s', '%s', '%s'); " 
                                        + "SET @id_val = (SELECT LAST_INSERT_ID()); "
                                        + "INSERT INTO single_appointment(appointment_id, location, end_date, end_time, description, start_date, start_time) "
                                        + "VALUES(@id_val, '%s', '%s', '%s', '%s', '%s', '%s') "    
                                    , name, date, time, location, endDate, endTime, description, startDate, time);

        //execute query and close connection
        Connection.executeUpdateQuery(query);
        Connection.close();
        
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
   
}
