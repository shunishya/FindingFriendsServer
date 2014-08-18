/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.daoimpl;

import com.findingfriends.SQLUtility.SQLUtility;
import com.findingfriends.dao.UserDao;
import com.findingfriends.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nitu
 */
public class UserDaoImplementation implements UserDao {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO user(`name`,`phonenumber`,`user_id`,`gps_lat`,`gps_long`,`time`) VALUES('" + user.getUserName() + "','" + user.getPhoneNumber() + "','" + user.getUser_id() + "','" + user.getGps_lat() + "','" + user.getGps_long() + "','" + user.getTime() + "');";
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            int a = stmt.executeUpdate(sql);
            if (a > 0) {
                //con.close();
                return true;
            }

        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public List<User> displayAllUser() {
        List<User> userList = new ArrayList<User>();

        String sql = "SELECT * from user";
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                String userName = rs.getString("name");
                String phoneNumber = rs.getString("phonenumber");
                String user_id = rs.getString("user_id");
                double gps_lat = rs.getDouble("gps_lat");
                double gps_long = rs.getDouble("gps_long");
                long time = rs.getLong("time");
                user.setPhoneNumber(phoneNumber);
                user.setUserName(userName);
                user.setGps_lat(gps_lat);
                user.setGps_long(gps_long);
                user.setUser_id(user_id);
                user.setTime(time);
                userList.add(user);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    @Override
    public boolean isUserPresent(String phoneNumber) {
        if (getUser(phoneNumber) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User getUser(String phoneNumber) {
        User user = null;
        String sql = "SELECT * from user WHERE `phonenumber`= '" + phoneNumber + "';";
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("name");
                String phoneNumbers = rs.getString("phonenumber");
                String userId = rs.getString("user_id");
                double gps_lat = rs.getDouble("gps_lat");
                double gps_long = rs.getDouble("gps_long");
                long time = rs.getLong("time");
                user = new User();
                user.setPhoneNumber(phoneNumbers);
                user.setUserName(userName);
                user.setGps_lat(gps_lat);
                user.setGps_long(gps_long);
                user.setUser_id(userId);
                user.setTime(time);

            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public boolean updateUser(String user_id, double gps_lat, double gps_long) {
        long time = System.currentTimeMillis();
        String sql = "UPDATE user SET gps_lat='" + gps_lat + "', gps_long='" + gps_long + "', time='" + time + "' where user_id='" + user_id + "';";
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            int a = stmt.executeUpdate(sql);
            if (a > 0) {
                return true;
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User getUserById(String user_id) {
        User user = null;
        String sql = "SELECT * from user WHERE `user_id`= '" + user_id + "';";
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("name");
                String phoneNumbers = rs.getString("phonenumber");
                String userId = rs.getString("user_id");
                double gps_lat = rs.getDouble("gps_lat");
                double gps_long = rs.getDouble("gps_long");
                long time = rs.getLong("time");
                user = new User();
                user.setPhoneNumber(phoneNumbers);
                user.setUserName(userName);
                user.setGps_lat(gps_lat);
                user.setGps_long(gps_long);
                user.setUser_id(user_id);
                user.setTime(time);

            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

}
