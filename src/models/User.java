package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User class
 * 
 * @author Simon Wiering
 */
public class User {
    private int ID;
    private String username;
    private String password;
    
    public void save() throws NoSuchAlgorithmException{
        //Make Connection
        DB Connection = new DB();
        String sql = String.format("INSERT INTO User (username, password)" +
                        "VALUES ('%s', '%s')",
                this.username, this.password);

        //execute query and close connection
        Connection.executeUpdateQuery(sql);
        Connection.close();
    }

    public boolean exists() throws SQLException{
        boolean exists;
        DB Connection = new DB();
        
        // Query to check if the user exists
        String sql = String.format("SELECT * FROM User WHERE `username` = '%s'", this.username);
        
        ResultSet queryResult = Connection.executeResultSetQuery(sql);

        exists = queryResult.first();

        Connection.close();

        return exists;
    }
    
    /**
     *
     * @param Password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hashPassword(String Password) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(Password.getBytes(), 0, Password.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    /**
     * Update user
     */
    public void Update() {
        // create connection
        DB Connection = new DB();

        // create update-query string
        String sql = String.format("UPDATE User SET `username` = '%s', `password` = '%s' WHERE `id` = %s",
                this.username, this.password, this.ID);

        // send query to database
        Connection.executeUpdateQuery(sql);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String Username){
        this.username = Username;
    }

    public void setPassword(String Password) throws NoSuchAlgorithmException{
        this.password = hashPassword(Password);
    }

    public void setHashedPassword(String Password){
        this.password = Password;
    }

    public int getID() {
        return ID;
    }

    public String getUsername(){
        return username;
    }


    public String getPassword(){
        return password;
}


    public boolean IsNotEmpty(){
        return this.username != null &&
                this.password != null;
    }

}