package main.java.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 *
 * @author Simon
 */
public class User {
    private int ID;
    private String Username;
    private String Password;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String Username){
        this.Username = Username;
    }

    public void setPassword(String Password) throws NoSuchAlgorithmException{
        this.Password = HashPassword(Password);
    }

    public void setHashedPassword(String Password){
        this.Password = Password;
    }

    public int getID() {
        return ID;
    }

    public String getUsername(){
        return Username;
    }


    public String getPassword(){
        return Password;
    }


    public boolean IsNotEmpty(){
        return this.Username != null &&
                this.Password != null;
    }

    public void Save() throws NoSuchAlgorithmException{
        //Make Connection
        DB Connection = new DB();
        String sql = String.format("INSERT INTO User (username, password)" +
                        "VALUES ('%s', '%s')",
                this.Username, this.Password);

        //execute query and close connection
        Connection.executeUpdateQuery(sql);
        Connection.close();
    }

    public boolean Exists() throws SQLException{
        boolean exists;
        DB Connection = new DB();
        //Simpele query om te kijken of er een record dubbel is, niet specifiek welke record.
        String sql = String.format("SELECT * FROM User WHERE `username` = '%s'", this.Username);
        ResultSet queryResult = Connection.executeResultSetQuery(sql);

        exists = queryResult.first();

        Connection.close();

        return exists;
    }

//    public boolean hasValidEmail(){
//        //mail has to match regex
//        Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
//                Pattern.CASE_INSENSITIVE);
//        return ptr.matcher(this.Email).matches();
//    }

    public static String HashPassword(String Password) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(Password.getBytes(), 0, Password.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    public void Update() {
        // create connection
        DB Connection = new DB();

        // create update-query string
        String sql = String.format("UPDATE User SET `username` = '%s', `password` = '%s' WHERE `id` = %s",
                this.Username, this.Password, this.ID);

        // send query to database
        Connection.executeUpdateQuery(sql);
    }
}