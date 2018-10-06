/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author jairz
 */
public class Auth extends Authenticator {
    
    private static Auth auth = new Auth( );

    /* private constructor prevents any other
     * class from instantiating.
     */
    private Auth() { }

    /* Static 'instance' method */
    public static Auth getInstance( ) {
       return auth;
    }

    public static boolean validate(String username, String password) throws SQLException {

         //create connection and execute query
        DB Connection = new DB();
        User user = new User();

        //this query will only select 1 row if the data is correct
        String sql = String.format("SELECT * FROM user WHERE `username` = '%s' AND `password` = '%s'",
                username, password);
        

        ResultSet queryResult = Connection.executeResultSetQuery(sql);

        System.out.println(queryResult.first());

        if(queryResult.first()) {
          
            //set properties of current User
            user.setUsername(queryResult.getString("username"));

            return true;
        } else {
            return false;
        }

    }
        
}        
  
