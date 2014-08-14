/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.findingfriends.SQLUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nitu
 */
public class SQLUtility {
     static String driver="com.mysql.jdbc.Driver";
   static String DB_Username="root";
   static String DB_password="";
   static String DB_URL="jdbc:mysql://localhost:3306/finding_friends";
    public static Connection getConnection() throws InstantiationException, IllegalAccessException, SQLException
    {
        
        Connection con=null;
        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        con=DriverManager.getConnection(DB_URL,DB_Username,DB_password);
        return con;
        
    }
    
}
